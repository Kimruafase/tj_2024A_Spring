package example.day07.todo;

public class ToDoDto {
    private int tNo;
    private String toDo;
    private int tState;

    public ToDoDto(){}

    public ToDoDto(int tNo, String toDo, int tState) {
        this.tNo = tNo;
        this.toDo = toDo;
        this.tState = 0;
    }

    public int gettNo() {
        return tNo;
    }

    public void settNo(int tNo) {
        this.tNo = tNo;
    }

    public String getToDo() {
        return toDo;
    }

    public void setToDo(String toDo) {
        this.toDo = toDo;
    }

    public int gettState() {
        return tState;
    }

    public void settState(int tState) {
        this.tState = tState;
    }

    @Override
    public String toString() {
        return "ToDoDto{" +
                "tNo=" + tNo +
                ", toDo='" + toDo + '\'' +
                ", tState=" + tState +
                '}';
    }
}
