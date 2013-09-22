package com.cromiumapps.uwhub;

public class StringCleaner {

	public static String replaceHTML(String string) {
		string = string.replace("&eacute;", "é");
		string = string.replace("&nbsp;", " ");
		string = string.replace("&amp;", "&");
		string = string.replace("&lt;", "<");
		string = string.replace("&gt;", ">");
		string = string.replace("&apos;", "'");
		string = string.replace("&lsquo;", "'");
		string = string.replace("&rsquo;", "'");
		string = string.replace("&quot;", "\"");
		string = string.replace("&ldquo;", "\"");
		string = string.replace("&rdquo;", "\"");
		string = string.replace("&ndash;", "-");
		string = string.replace("&hellip;", "...");

		string = string.replaceAll("<.*?>","");

		return string;
	}

	public static String removeOfferings(String string) {
		string = string.replaceAll("\\[Offered.*\\]", "");
		string = string.replaceAll("\\[Also offered.*\\]", "");

		return string;
	}

	public static String cleanContent(String string) {
		string = replaceHTML(string);

		string = string.replaceAll("^, ","");
		string = string.replace("\"\"","\"");
		string = string.replace(" Details.","");
		string = string.replace("&#x27;s","");

		return string;
	}

	public static String getLink(String string) {
		string = string.replace("{\"result\":[\"","");
		string = string.replace("\"]}","");
		string = string.replace("\\/","/");

		return string;
	}
}