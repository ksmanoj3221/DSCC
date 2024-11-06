package dscc5;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MathEvaluatorImpl extends UnicastRemoteObject implements MathEvaluator {

    protected MathEvaluatorImpl() throws RemoteException {
        super();
    }

    @Override
    public double evaluate(String expression) throws RemoteException {
        try (Context context = Context.create()) {
            Value result = context.eval("js", expression);
            return result.asDouble();
        } catch (Exception e) {
            throw new RemoteException("Error evaluating expression", e);
        }
    }
}
