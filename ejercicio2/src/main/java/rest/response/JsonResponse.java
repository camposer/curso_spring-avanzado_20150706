package rest.response;

public class JsonResponse {
	private boolean success;
	private Object data;
	
	public JsonResponse() {
	
	}
	
	private JsonResponse(boolean success, Object data) {
		this.success = success;
		this.data = data;
	}
	
	public static JsonResponse success(Object data) {
		return new JsonResponse(true, data);
	}

	public static JsonResponse error(String mensaje) {
		return new JsonResponse(false, mensaje);
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
