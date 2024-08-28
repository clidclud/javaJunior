package sem3.task2;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ToDo implements Externalizable {

    private String title;
    private boolean isDone;

    public ToDo(){
        this.title = "";
        this.isDone = false;
    }

    public ToDo(String title) {
        this.title = title;
        this.isDone = false;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeBoolean(isDone);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        isDone = in.readBoolean();
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
