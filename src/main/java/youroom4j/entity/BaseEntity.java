package youroom4j.entity;

/**
 * Having base entity method.
 * @author Shintaro Katafuchi
 */
class BaseEntity {

	BaseEntity() {

	}

	/**
	 * Check arugumet is null or empty.
	 * @param argument
	 * @return if argument is null or empty then true.
	 */
	protected boolean isNullOrEmpty(String argumet) {
		return argumet == null || "".equals(argumet);
	}

}