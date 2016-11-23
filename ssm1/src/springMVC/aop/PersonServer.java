package springMVC.aop;

public interface PersonServer {
	public void save(String name);  
    public void update(String name, Integer id);  
    public String getPersonName(Integer id);
	
}
