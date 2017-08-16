
public class CurrencyQuerymaker {

	private String devoloped = "developed";
	private String devoloping = "developing";
	private String underdeveloped = "underdeveloped";

	public String get_startwith_query(String type, String query, String letter) {

		String temp[] = null;
		int flag = 0;

		if (!type.equals("any") && query.length() > 0) {
			if (query.contains(underdeveloped)) {
				temp = query.split(underdeveloped);
				flag = 1;
			} else if (query.contains(devoloped)) {

				temp = query.split(devoloped);
				flag = 1;
			} else if (query.contains(devoloping)) {
				temp = query.split(devoloping);

				flag = 1;
			}

			if (flag == 1) {
				query = temp[0] + temp[1].substring(4);
				query = set_comma(query);
				query = type + "(X).";
			}

			return query;
		}

		if (type.equals("any")) {
			if (query.contains(underdeveloped)) {
				temp = query.split(underdeveloped);
				flag = 1;
			} else if (query.contains(devoloped)) {

				temp = query.split(devoloped);
				flag = 1;
			} else if (query.contains(devoloping)) {
				temp = query.split(devoloping);

				flag = 1;
			}

			if (flag == 1) {
				query = temp[0] + temp[1].substring(4);
				query = set_dot(query);
				return query;
			} else
				return query;
		}

		if (!type.equals("any") && query.length() == 0) {
			query = type + "(X).";
			return query;
		}

		if (query.contains(underdeveloped)) {
			temp = query.split(underdeveloped);
			flag = 1;
		} else if (query.contains(devoloped)) {

			temp = query.split(devoloped);
			flag = 1;
		} else if (query.contains(devoloping)) {
			temp = query.split(devoloping);

			flag = 1;
		}

		if (flag == 1) {

			if (type.equals("any")) {
				query = temp[0] + temp[1].substring(4);
				query = set_dot(query);
			} else {
				query = temp[0] + temp[1].substring(4);
				query = set_comma(query);
				query = type + "(X).";
			}
			return query;
		} else {
			query = set_comma(query);
			query = type + "(X).";
			return query;
		}

	}

	private String set_dot(String org_query) {
		String query = org_query;

		if (!(query.length() > 0)) {
			return query;
		}
		char[] charQuery = query.toCharArray();
		charQuery[charQuery.length - 1] = '.';
		query = String.valueOf(charQuery);

		return query;

	}

	private String set_comma(String org_query) {

		String query = org_query;

		if (!(query.length() > 0)) {
			return query;
		}

		char[] charQuery = query.toCharArray();
		charQuery[charQuery.length - 1] = ',';
		query = String.valueOf(charQuery);

		return query;
	}

}
