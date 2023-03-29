import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CalculateDates1a {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		String startDate = "20170220";
		String closeDate = "20211224";
		int addMonth = 2;
		// 1.a
		int totalDays = calculateDates(startDate, closeDate);
		// 1.b
		String calculateCloseDate = calculateCloseDate(startDate, addMonth);
		System.out
				.println("Total days from " + startDate + " to " + "closeDate (included closeDate)) is: " + totalDays);
		System.out.println("Calculate closeDate after adding " + addMonth + " months is: " + calculateCloseDate);

	}

	public static String calculateCloseDate(String startDate, int addMonth) {
		if (isValidDate(startDate) == false) {
			System.out.println("startDate is not valid date!!!!");
			return "NotValidDate!!!";
		}
		int startDateInt = Integer.parseInt(startDate);
		int startYear = startDateInt / 10000;
		int startMonth = (startDateInt % 10000) / 100;
		;
		int startDay = startDateInt % 100;
		int remainMonth = 0;
		int closeMonth = startMonth;
		int closeDay = startDay;
		int closeYear = startYear;
		int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		closeYear += addMonth / 12;
		remainMonth = addMonth % 12;

		while (remainMonth > 0) {
			if (closeMonth == 12) {
				closeYear += 1;
				closeMonth = 1;
			}
			if (daysInMonths[startMonth - 1] == startDay) {
				daysInMonths[1] += isLeapYear(closeYear) ? 1 : 0;
				closeDay = daysInMonths[closeMonth];
			}
			remainMonth -= 1;
			closeMonth += 1;
		}

		String closeYearStr = String.format("%02d", closeYear);
		String closeMonthStr = String.format("%02d", closeMonth);
		String closeDayStr = String.format("%02d", closeDay);
		List<String> tmp = Arrays.asList(closeYearStr, closeMonthStr, closeDayStr);
		String closeDate = tmp.stream().map(String::valueOf).collect(Collectors.joining(""));

		return closeDate;
	}

	// This function calculate all days from startDate to closeDate (includes the
	// lastday)
	public static int calculateDates(String startDate, String closeDate) {
		// Check startDate is valid date or not
		if (isValidDate(startDate) == false) {
			System.out.println("startDate is not valid date!!!");
			return -1;
		}
		// Check closeDate is valid date or not
		if (isValidDate(closeDate) == false) {
			System.out.println("closeDate is not valid date!!!");
			return -1;
		}
		int startDateInt = Integer.parseInt(startDate);
		int closeDateInt = Integer.parseInt(closeDate);

		// Check startDate must come before closeDate
		if (closeDateInt < startDateInt) {
			System.out.println("closeDate, startDate is not valid date!!!");
			return -1;
		} else if (closeDateInt == startDateInt) {
			return 1; // if closeDate == startDate -> return 1 (day)
		}

		int startRemainDay = 0;
		int closeRemainDay = 0;
		int fullDays = 0;
		int totalDays = 0;
		int startYear = startDateInt / 10000;
		int closeYear = closeDateInt / 10000;

		if (startYear < closeYear) {
			startRemainDay = calculateStartRemainDays(startDateInt);
			closeRemainDay = calculateCloseRemainDays(closeDateInt);

			startYear = startYear + 1;

			while (startYear < closeYear) {
				if (isLeapYear(startYear)) {
					fullDays += 366;
				} else {
					fullDays += 365;
				}
				startYear += 1;
			}
			System.out.println("DEBUG: Full Days: " + fullDays);
			System.out.println("DEBUG: startRemainDay: " + startRemainDay);
			System.out.println("DEBUG: closeRemainDay: " + closeRemainDay);
			totalDays = startRemainDay + fullDays + closeRemainDay;
		} else {
			// Calculate all days in case both startDate and closeDate are in the same year
			totalDays = calculateStartCloseDayInOneYear(startDateInt, closeDateInt);
		}
		return totalDays;

	}

	public static int calculateStartCloseDayInOneYear(int startDateInt, int closeDateInt) {
		int startYear = startDateInt / 10000;
		int startMonth = (startDateInt % 10000) / 100;
		;
		int startDay = startDateInt % 100;
		int remainDay = 0;

		int closeYear = closeDateInt / 10000;
		int closeMonth = (closeDateInt % 10000) / 100;
		;
		int closeDay = closeDateInt % 100;

		if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(startMonth)) {
			remainDay += 31 - startDay + 1;
		} else if (Arrays.asList(4, 6, 9, 11).contains(startMonth)) {
			remainDay += 30 - startDay + 1;
		} else {
			if (isLeapYear(startYear))
				remainDay += 29 - startDay + 1;
			else {
				remainDay += 28 - startDay + 1;
			}
		}
		System.out.println("DEBUG: calculateStartCloseDayInOneYear: " + remainDay);

		while (startMonth < closeMonth - 1) {
			startMonth += 1;
			if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(startMonth)) {
				remainDay += 31;
			} else if (Arrays.asList(4, 6, 9, 11).contains(startMonth)) {
				remainDay += 30;
			} else {
				if (isLeapYear(startYear))
					remainDay += 29;
				else {
					remainDay += 28;
				}
			}

			System.out.println("DEBUG: calculateStartCloseDayInOneYear: " + startMonth + ": " + remainDay);

		}

		return (remainDay + closeDay);

	}

	public static int calculateStartRemainDays(int startDateInt) {
		int startYear = startDateInt / 10000;
		int startMonth = (startDateInt % 10000) / 100;
		;
		int startDay = startDateInt % 100;
		int startRemainDay = 0;

		if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(startMonth)) {
			startRemainDay += 31 - startDay + 1;
		} else if (Arrays.asList(4, 6, 9, 11).contains(startMonth)) {
			startRemainDay += 30 - startDay + 1;
		} else {
			if (isLeapYear(startYear))
				startRemainDay += 29 - startDay + 1;
			else {
				startRemainDay += 28 - startDay + 1;
			}
		}
		System.out.println("DEBUG: startRemainDay: " + startRemainDay);

		while (startMonth < 12) {
			startMonth += 1;
			if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(startMonth)) {
				startRemainDay += 31;
			} else if (Arrays.asList(4, 6, 9, 11).contains(startMonth)) {
				startRemainDay += 30;
			} else {
				if (isLeapYear(startYear))
					startRemainDay += 29;
				else {
					startRemainDay += 28;
				}
			}

			System.out.println("DEBUG: startRemainDay " + startMonth + ": " + startRemainDay);

		}

		return startRemainDay;

	}

	public static int calculateCloseRemainDays(int closeDateInt) {
		int closeYear = closeDateInt / 10000;
		int closeMonth = (closeDateInt % 10000) / 100;
		;
		int closeDay = closeDateInt % 100;
		int closeRemainDay = closeDay;

		System.out.println("DEBUG: closeRemainDay " + closeRemainDay);
		closeMonth = closeMonth - 1;
		while (closeMonth > 0) {
			if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(closeMonth)) {
				closeRemainDay += 31;
			} else if (Arrays.asList(4, 6, 9, 11).contains(closeMonth)) {
				closeRemainDay += 30;
			} else {
				if (isLeapYear(closeYear))
					closeRemainDay += 29;
				else {
					closeRemainDay += 28;
				}
			}

			System.out.println("DEBUG: closeRemainDay " + closeMonth + ": " + closeRemainDay);
			closeMonth -= 1;
		}

		return closeRemainDay;
	}

	public static boolean isLeapYear(int year) {
		boolean result = false;
		if (year % 100 == 0) {
			result = (year % 400 == 0) ? true : false;
		} else if (year % 4 == 0) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	public static boolean isValidDate(String date) {
		int dateInt = Integer.parseInt(date);
		int year;
		int month;
		int day;
		if (dateInt < 0) {
			return false;
		}
		year = dateInt / 10000;
		month = (dateInt % 10000) / 100;
		day = dateInt % 100;
		if (month < 1 || month > 12) {
			return false;
		}
		if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(month)) {
			if (day > 31) {
				return false;
			}
		} else if (Arrays.asList(4, 6, 9, 11).contains(month)) {
			if (day > 30) {
				return false;
			}
		} else {
			if (isLeapYear(year)) {
				if (day > 29) {
					return false;
				}
			} else {
				if (day > 28) {
					return false;
				}
			}
		}

		return true;
	}

}
