package youroom4j.entity;

/**
 * Have base entity method.
 *
 * @author Shintaro Katafuchi
 */
abstract class BaseEntity {

	BaseEntity() {

	}

	/**
	 * Check arugumet is null or empty.
	 *
	 * @param argument
	 * @return if argument is null or empty then true.
	 */
	protected boolean isNullOrEmpty(String argumet) {
		return argumet == null || argumet.length() == 0;
	}

}