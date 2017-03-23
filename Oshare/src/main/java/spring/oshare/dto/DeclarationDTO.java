package spring.oshare.dto;

public class DeclarationDTO {
	private int declarationNo; //번호
	private String declarationSubject; //신고자
	private String declarationReporter; //신고대상자
	private String declarationType; //산고 유형
	private String declarationReason;  //신고 이유
	public int getDeclarationNo() {
		return declarationNo;
	}
	public void setDeclarationNo(int declarationNo) {
		this.declarationNo = declarationNo;
	}
	public String getDeclarationSubject() {
		return declarationSubject;
	}
	public void setDeclarationSubject(String declarationSubject) {
		this.declarationSubject = declarationSubject;
	}
	public String getDeclarationReporter() {
		return declarationReporter;
	}
	public void setDeclarationReporter(String declarationReporter) {
		this.declarationReporter = declarationReporter;
	}
	public String getDeclarationType() {
		return declarationType;
	}
	public void setDeclarationType(String declarationType) {
		this.declarationType = declarationType;
	}
	public String getDeclarationReason() {
		return declarationReason;
	}
	public void setDeclarationReason(String declarationReason) {
		this.declarationReason = declarationReason;
	}
	
	
}
