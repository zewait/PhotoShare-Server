package here.wait.photo.share.dao;

import here.wait.photo.share.bean.PhoneMessage;

import java.util.List;

public interface PhoneMessageDAO 
{
	public void save(PhoneMessage pm);
	public List<PhoneMessage> getAll();
}
