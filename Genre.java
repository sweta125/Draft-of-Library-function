/**
 * A genre
 *
 * @author Ray Sun, Sweta Kotha
 */
public enum Genre {
	NONFICTION {
		@Override
		/**
		 * Generates a string representation of the enum
		 * @return "nonfiction"
		 */
		public String toString() {
			return "Nonfiction";
		}
	},
	MYSTERY {
		@Override
		/**
		 * Generates a string representation of the enum
		 * @return "mystery"
		 */
		public String toString() {
			return "Mystery";
		}
	},
	SCIENCE_FICTION {
		@Override
		/**
		 * Generates a string representation of the enum
		 * @return "science fiction"
		 */
		public String toString() {
			return "Science Fiction";
		}
	},
	FANTASY {
		@Override
		/**
		 * Generates a string representation of the enum
		 * @return "fantasy"
		 */
		public String toString() {
			return "Fantasy";
		}
	},
	THRILLER {
		@Override
		/**
		 * Generates a string representation of the enum
		 * @return "thriller"
		 */
		public String toString() {
			return "Thriller";
		}
	}
}
