package forum.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Count the color from the session String
 * 
 * @ClassName: ColorCount
 * @author: CN.CSQ
 * @version: 1.0
 * @date: 2020-11-14
 */
public class PostFilter extends SimpleTagSupport {

	private String color;

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * 
	 * 
	 * @Title: doTag
	 * @throws JspException
	 * @throws IOException
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		List<Color> colorList = new ArrayList<Color>();

		String[] split = color.split(", ");

		for (int i = 0; i < split.length; i++) {
			// Jump over blank
			if (!split[i].equals("") && !split[i].equals(" ")) {

				boolean hasColor = false;
				Color now = null;
				for (int j = 0; j < colorList.size(); j++) {
					now = colorList.get(j);

					// Find same color
					if (split[i].equals(now.getColor())) {
						hasColor = true;
						break;
					}
				}

				if (hasColor == true) {
					// Exist color
					now.setNumber(now.getNumber() + 1);
				} else {
					// New color
					now = new Color(split[i], 1);
					colorList.add(now);
				}

			}
		}

		// If no color exist
		if (colorList.size() == 0) {
			getJspContext().getOut().print("No exist color.");
		} else {
			for (Color now : colorList) {
				getJspContext().getOut().print(now + "<hr>");
			}
		}
	}
}

class Color {
	public String color;
	public int number;

	/**
	 * 
	 * 
	 * @Title: Color
	 * @param color
	 * @param number
	 */
	public Color(String color, int number) {
		this.color = color;
		this.number = number;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * 
	 * 
	 * @Title: toString
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return color + " " + number;
	}

}
