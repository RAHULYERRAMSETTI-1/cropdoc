package andy.nasa.models;

import andy.nasa.main.widget.ContactItemInterface;

public class ExampleContactItem implements ContactItemInterface{

	private String nickName;
	private String fullName;
	private String URL;
	
	public ExampleContactItem(String nickName, String fullName,String URL) {
		super();
		this.nickName = nickName;
		this.setFullName(fullName);
		this.URL=URL;
	}

	// index the list by nickname
	public String getURL()
	{
		return URL;
	}
	
	@Override
	public String getItemForIndex() {
		return nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
