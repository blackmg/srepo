package vision.srepo.diffsystem;

/**
 * Created by IntelliJ IDEA.
 * User: johan
 * Date: 2010-mar-08
 * Time: 14:34:45
 * To change this template use File | Settings | File Templates.
 */
public abstract class Operation implements Runnable {

    enum Type {
        PULL, PUSH
    }

    private Type type;


}
