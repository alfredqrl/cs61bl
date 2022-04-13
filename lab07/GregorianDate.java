public class GregorianDate extends Date {

    private static final int[] MONTH_LENGTHS = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }


    // YOUR CODE HERE
    public GregorianDate nextDate() {
        int newDay, newMonth, newYear;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (dayOfMonth == 31){
                newDay = 1;
                newMonth = month + 1;
            }
            else {
                newDay = dayOfMonth + 1;
                newMonth = month;
            }
        } else if (month == 2){
            if (dayOfMonth == 28) {
                newDay = 1;
                newMonth = month + 1;
            }
            else{
                newDay = dayOfMonth + 1;
                newMonth = month;
            }
        }
        else{
            if (dayOfMonth == 30){
                newDay = 1;
                newMonth = month + 1;
            }
            else{
                newDay = dayOfMonth + 1;
                newMonth = month;
            }
        }
        if (newMonth == 13) {
            newMonth = 1;
            newYear = year + 1;
        } else {
            newYear = year;
        }
        GregorianDate gregorianDate = new GregorianDate(newYear, newMonth, newDay);
        return gregorianDate;
    }

    @Override
    public int dayOfYear() {
        int precedingMonthDays = 0;
        for (int m = 1; m < month; m += 1) {
            precedingMonthDays += getMonthLength(m);
        }
        return precedingMonthDays + dayOfMonth;
    }

    private static int getMonthLength(int m) {
        return MONTH_LENGTHS[m - 1];
    }
}