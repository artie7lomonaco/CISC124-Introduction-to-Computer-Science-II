package a2;

import java.awt.Color;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class A2 {
	/**
	 * The original image
	 */
	private static Picture orig;

	/**
	 * The image viewer class
	 */
	private static A2Viewer viewer;

	/**
	 * Returns a 300x200 image containing the Queen's flag (without the crown).
	 * 
	 * @return an image containing the Queen's flag
	 */
	public static Picture flag() {
		Picture img = new Picture(300, 200);
		int w = img.width();
		int h = img.height();

		// set the pixels in the blue stripe
		Color blue = new Color(0, 48, 95);
		for (int col = 0; col < w / 3; col++) {
			for (int row = 0; row < h - 1; row++) {
				img.set(col, row, blue);
			}
		}

		// set the pixels in the yellow stripe
		Color yellow = new Color(255, 189, 17);
		for (int col = w / 3; col < 2 * w / 3; col++) {
			for (int row = 0; row < h - 1; row++) {
				img.set(col, row, yellow);
			}
		}

		// set the pixels in the red stripe
		Color red = new Color(185, 17, 55);
		for (int col = 2 * w / 3; col < w; col++) {
			for (int row = 0; row < h - 1; row++) {
				img.set(col, row, red);
			}
		}
		return img;
	}

	public static Picture copy(Picture p) {
		Picture result = new Picture(p.width(), p.height());
		// complete the method
		for (int col = 0; col < p.width(); col++) {
			for (int row = 0; row < p.height(); row++) {
				result.set(col, row, p.get(col, row));
			}
		}

		return result;
	}

	// ADD YOUR METHODS HERE

	// returns a specified border
	public static Picture border(Picture p, int b) {
		Picture result = new Picture(p.width(), p.height());
		Color blue = new Color(0, 48, 95);
		for (int col = 0; col < p.width(); col++) {
			for (int row = 0; row < p.height(); row++) {
				if (col <= b || col >= p.width() - 1 - b || row <= b || row >= p.height() - 1 - b) {
					result.set(col, row, blue);
				} else {
					result.set(col, row, p.get(col, row));
				}
			}
		}

		return result;
	}

	// returns the original image but in greyscale
	public static Picture grayScale(Picture p) {
		Picture result = new Picture(p.width(), p.height());
		for (int col = 0; col < p.width(); col++) {
			for (int row = 0; row < p.height(); row++) {
				Color color = p.get(col, row);
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				int y = (int) Math.round((0.2989 * r) + (0.5870 * g) + (0.1140 * b));
				Color newCol = new Color(y, y, y);
				result.set(col, row, newCol);

			}
		}
		return result;
	}

	// returns binary version of image
	public static Picture binary(Picture p, Color c1, Color c2) {
		Picture result = new Picture(p.width(), p.height());
		Picture grayPic = A2.grayScale(A2.orig);
		for (int col = 0; col < p.width(); col++) {
			for (int row = 0; row < p.height(); row++) {
				Color color = grayPic.get(col, row);
				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();
				int y = (int) Math.round((0.2989 * r) + (0.5870 * g) + (0.1140 * b));
				if (y < 128) {
					result.set(col, row, Color.black);
				} else {
					result.set(col, row, Color.white);
				}
			}
		}
		return result;
	}

	// flips the image vertically
	public static Picture vFlip(Picture p) {
		Picture result = new Picture(p.width(), p.height());
		// complete the method
		for (int col = 0; col < p.width(); col++) {
			for (int row = 0; row < p.height(); row++) {
				result.set(col, p.height() - 1 - row, p.get(col, row));
			}
		}

		return result;
	}

	// rotates image 90 degrees
	public static Picture rotate90(Picture p) {
		Picture result = new Picture(p.height(), p.width());
		for (int col = 0; col < p.width(); col++) {
			for (int row = 0; row < p.height(); row++) {
				result.set(p.height() - 1 - row, col, p.get(col, row));
			}
		}

		return result;
	}

	// removes red-eye effect from images
	public static Picture redEye(Picture p) {
		Picture result = new Picture(p.width(), p.height());
		for (int col = 0; col < p.width(); col++) {
			for (int row = 0; row < p.height(); row++) {
				Color c = p.get(col, row);
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				/*
				 * Found a formula online that returns a red intensity value which can be used
				 * to detect the red eye. If the value is greater than 2.2, then it turns the
				 * pixel black. The problem with this code is that it doesn't register the
				 * center of the little girls eyes since that part of them are white and purple
				 * but not red, so it removes all the red around it instead.
				 */
				double redIntense = ((double) r / ((g + b) / 2.0));
				if (redIntense > 2.2) {
					result.set(col, row, Color.black);
				} else {
					result.set(col, row, p.get(col, row));
				}
			}
		}

		return result;
	}

	public static Picture blur(Picture p, int radius) {
		Picture result = new Picture(p.width(), p.height());
		Color c;
		for (int col = 0; col < p.width(); col++) {
			for (int row = 0; row < p.height(); row++) {
				int redCount = 0;
				int greenCount = 0;
				int blueCount = 0;
				int newRed = 0;
				int newGreen = 0;
				int newBlue = 0;
				int div = 0;
				for (int i = col - radius; i < col + radius; i++) {
					for (int j = row - radius; j < row + radius; j++) {
						if (i < 0 || j < 0 || i > p.width() - 1 || j > p.height() - 1) {
							redCount += 0;
							greenCount += 0;
							blueCount += 0;
						} else {
							c = p.get(i, j);
							redCount += c.getRed();
							greenCount += c.getGreen();
							blueCount += c.getBlue();
							div += 1;
						}
					}
				}
				newRed = (int) Math.round(redCount / div);
				newGreen = (int) Math.round(greenCount / div);
				newBlue = (int) Math.round(blueCount / div);
				Color blur = new Color(newRed, newGreen, newBlue);
				result.set(col, row, blur);
			}
		}

		return result;
	}

	/**
	 * A2Viewer class calls this method when a menu item is selected. This method
	 * computes a new image and then asks the viewer to display the computed image.
	 * 
	 * @param op the operation selected in the viewer
	 */
	public static void processImage(String op) {

		switch (op) {
		case A2Viewer.FLAG:
			// create a new image by copying the original image
			Picture p = A2.flag();
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.COPY:
			// create a new image by copying the original image
			p = A2.copy(A2.orig);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.BORDER_1:
			// create a new image by adding a border of width 1 to the original image
			p = A2.border(A2.orig, 1);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.BORDER_5:
			// create a new image by adding a border of width 5 the original image
			p = A2.border(A2.orig, 5);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.BORDER_10:
			// create a new image by adding a border of width 10 the original image
			p = A2.border(A2.orig, 10);
			A2.viewer.setComputed(p);
			break;
		case A2Viewer.TO_GRAY:
			// create a new image by converting the original image to grayscale
			p = A2.grayScale(A2.orig);
			A2.viewer.setComputed(p);

			break;
		case A2Viewer.TO_BINARY:
			// create a new image by converting the original image to black and white
			p = A2.binary(A2.orig, Color.black, Color.white);
			A2.viewer.setComputed(p);

			break;
		case A2Viewer.FLIP_VERTICAL:
			// create a new image by flipping the original image vertically
			p = A2.vFlip(A2.orig);
			A2.viewer.setComputed(p);

			break;
		case A2Viewer.ROTATE_RIGHT:
			// create a new image by rotating the original image to the right by 90 degrees
			p = A2.rotate90(A2.orig);
			A2.viewer.setComputed(p);

			break;
		case A2Viewer.RED_EYE:
			// create a new image by removing the redeye effect in the original image
			p = A2.redEye(A2.orig);
			A2.viewer.setComputed(p);

			break;
		case A2Viewer.BLUR_1:
			// create a new image by blurring the original image with a box blur of radius 1
			p = A2.blur(A2.orig, 1);
			A2.viewer.setComputed(p);

			break;
		case A2Viewer.BLUR_3:
			// create a new image by blurring the original image with a box blur of radius 3
			p = A2.blur(A2.orig, 3);
			A2.viewer.setComputed(p);

			break;
		case A2Viewer.BLUR_5:
			// create a new image by blurring the original image with a box blur of radius 5
			p = A2.blur(A2.orig, 5);
			A2.viewer.setComputed(p);

			break;
		default:
			// do nothing
		}
	}

	/**
	 * Starting point of the program. Students can comment/uncomment which image to
	 * use when testing their program.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		A2.viewer = new A2Viewer();
		A2.viewer.setVisible(true);

		URL img;
		// uncomment one of the next two lines to choose which test image to use (person
		// or cat)
		// img = A2.class.getResource("redeye-400x300.jpg");
		img = A2.class.getResource("cat.jpg");

		try {
			URI uri = new URI(img.toString());
			A2.orig = new Picture(new File(uri.getPath()));
			A2.viewer.setOriginal(A2.orig);
		} catch (Exception x) {

		}
	}

}
