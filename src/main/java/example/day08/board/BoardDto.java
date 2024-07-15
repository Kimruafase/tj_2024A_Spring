package example.day08.board;

public class BoardDto {
    private int pno;
    private String ptitle;
    private String pdate;
    private int pview;
    private String pdetail;
    private String ppwd;

    public BoardDto(){}

    public BoardDto(int pno, String ptitle, String pdate, int pview, String pdetail, String ppwd) {
        this.pno = pno;
        this.ptitle = ptitle;
        this.pdate = pdate;
        this.pview = pview;
        this.pdetail = pdetail;
        this.ppwd = ppwd;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public int getPview() {
        return pview;
    }

    public void setPview(int pview) {
        this.pview = pview;
    }

    public String getPdetail() {
        return pdetail;
    }

    public void setPdetail(String pdetail) {
        this.pdetail = pdetail;
    }

    public String getPpwd() {
        return ppwd;
    }

    public void setPpwd(String ppwd) {
        this.ppwd = ppwd;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "pno=" + pno +
                ", ptitle='" + ptitle + '\'' +
                ", pdate='" + pdate + '\'' +
                ", pview=" + pview +
                ", pdetail='" + pdetail + '\'' +
                ", ppwd='" + ppwd + '\'' +
                '}';
    }
}
