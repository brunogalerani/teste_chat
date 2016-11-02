package App;

import java.io.Serializable;
import java.util.function.Consumer;

public abstract class Server {
	
	private Consumer<Serializable> onReceiveCallBack;
}
