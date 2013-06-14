package here.wait.photo.share.utils;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Component("imageZoomUtils")
public class ImageZoomUtility
{
	private File file = null; // 文件对象
	private String inputDir; // 输入图路径
								// 此处若是文件上传的话把文件的输入路径也设置成服务器端的路径（就是在文件上传完成后做处理）这样会便于你去做图片的处理，因为本段代码并不支持url的解析
	private String outputDir; // 输出图路径也可以在这个里面加上一段url的解析处理，这样你的输出路径就会即支持url，也支持本地的缩放
	private String inputFileName; // 输入图文件名
	private String outputFileName; // 输出图文件名
	private int outputWidth = 300; // 默认输出图片宽
	private int outputHeight = 300; // 默认输出图片高
	private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)
	private InputStream in;
	private String intputPath;
	private String outputPath;
	
	
	public ImageZoomUtility()
	{ // 初始化变量
		inputDir = "";
		outputDir = "";
		inputFileName = "";
		outputFileName = "";
		in = null;
	}
	
	
	
	public void setInputDir(String inputDir)
	{
		this.inputDir = inputDir;
	}

	public void setOutputDir(String outputDir)
	{
		this.outputDir = outputDir;
	}

	public void setInputFileName(String inputFileName)
	{
		this.inputFileName = inputFileName;
	}

	public void setOutputFileName(String outputFileName)
	{
		this.outputFileName = outputFileName;
	}

	public void setOutputWidth(int outputWidth)
	{
		this.outputWidth = outputWidth;
	}

	public void setOutputHeight(int outputHeight)
	{
		this.outputHeight = outputHeight;
	}

	public void setWidthAndHeight(int width, int height)
	{
		this.outputWidth = width;
		this.outputHeight = height;
	}

	public boolean isProportion()
	{
		return proportion;
	}

	public void setProportion(boolean proportion)
	{
		this.proportion = proportion;
	}

	public InputStream getIn()
	{
		return in;
	}

	public void setIn(InputStream in)
	{
		this.in = in;
	}

	public long getPicSize(String path)
	{
		file = new File(path);
		return file.length();
	}

	public String getInputDir()
	{
		return inputDir;
	}

	public String getOutputDir()
	{
		return outputDir;
	}

	public String getInputFileName()
	{
		return inputFileName;
	}

	public String getOutputFileName()
	{
		return outputFileName;
	}

	public int getOutputWidth()
	{
		return outputWidth;
	}

	public int getOutputHeight()
	{
		return outputHeight;
	}
	
	// 图片处理
	public String getIntputPath()
	{
		return intputPath;
	}



	public void setIntputPath(String intputPath)
	{
		this.intputPath = intputPath;
	}



	public String getOutputPath()
	{
		return outputPath;
	}



	public void setOutputPath(String outputPath)
	{
		this.outputPath = outputPath;
	}



	public String zoomPicture()
	{
		FileOutputStream out = null;
		try
		{
			// 获得源文件
//			file = new File(inputDir + inputFileName);
			file = new File(intputPath);
			if (!file.exists())
			{
				return "input";
			}
			
			in = new FileInputStream(file);

			Image img = ImageIO.read(in); // 如果是本地图片处理的话，这个地方直接把file放到ImageIO.read(file)中即可，如果是执行上传图片的话，
											// Formfile formfile=获得表单提交的Formfile
											// ,然后 ImageIO.read 方法里参数放
											// formfile.getInputStream()
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1)
			{
				return "input";
			} else
			{
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放
				if (this.proportion == true)
				{
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null))
							/ (double) outputWidth + 0.1;
					double rate2 = ((double) img.getHeight(null))
							/ (double) outputHeight + 0.1;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else
				{
					newWidth = outputWidth; // 输出的图片宽度
					newHeight = outputHeight; // 输出的图片高度
				}
				BufferedImage tag = new BufferedImage((int) newWidth,
						(int) newHeight, BufferedImage.TYPE_INT_RGB);

				tag.getGraphics().drawImage(
						img.getScaledInstance(newWidth, newHeight,
								Image.SCALE_SMOOTH), 0, 0, null);
				out = new FileOutputStream(outputPath);
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				
			}
		} catch (IOException ex)
		{
			ex.printStackTrace();
			return "input";
		}
		finally
		{
			if(null != out)
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return "success";
	}

	public String zoomPicture(String inputDir, String outputDir,
			String inputFileName, String outputFileName, int width, int height,
			boolean gp, InputStream in)
	{
		// 输入图路径
		this.inputDir = inputDir;
		// 输出图路径
		this.outputDir = outputDir;
		// 输入图文件名
		this.inputFileName = inputFileName;
		// 输出图文件名
		this.outputFileName = outputFileName;
		this.in = in;
		// 设置图片长宽
		setWidthAndHeight(width, height);
		// 是否是等比缩放标记
		this.proportion = gp;
		return zoomPicture();
	}

}