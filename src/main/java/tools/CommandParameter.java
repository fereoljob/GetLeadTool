package tools;

public class CommandParameter {

	private ScanType type;
	private Object value;
	public CommandParameter(ScanType type) {
		super();
		this.type = type;
	}
	public ScanType getType() {
		return type;
	}
	public void setType(ScanType type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	
	
	
}
