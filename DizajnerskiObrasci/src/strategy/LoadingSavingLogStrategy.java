package strategy;

public interface LoadingSavingLogStrategy {
	public String load(String filePath);
	public void save(String save, String filePath);

}
