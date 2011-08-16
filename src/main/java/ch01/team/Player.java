package ch01.team;

public class Player {
private String name;
private String nickName;

public Player()
{
	
}

public Player(String name,String nickname)
{
	setName(name);
	setNickName(nickname);
}


public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getNickName() {
	return nickName;
}
public void setNickName(String nickName) {
	this.nickName = nickName;
}

}
