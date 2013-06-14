package here.wait.photo.share.service;

import here.wait.photo.share.bean.PhoneMessage;

import java.util.List;

public interface PhoneMessageService 
{
	public String add(PhoneMessage pm);
	public List<PhoneMessage> getAll();
}
