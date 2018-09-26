/**
 * A shelf location
 *
 * @author Ray Sun, Sweta Kotha
 */
public enum Location {
	CHILDREN {
		@Override
		/**
		 * Generates a string representation of the enum
		 * @return "children"
		 */
		public String toString() {
			return "children section";
		}
	},
	ADULT {
		@Override
		/**
		 * Generates a string representation of the enum
		 * @return "adult"
		 */
		public String toString() {
			return "adult section";
		}
	},
	REFERENCE {
		@Override
		/**
		 * Generates a string representation of the enum
		 * @return "reference"
		 */
		public String toString() {
			return "reference section";
		}
	}
}
