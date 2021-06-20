package app.ui.console.utils;

import app.domain.model.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {

    static public String readLineFromConsole(String prompt)
    {
        try
        {
            System.out.println("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt)
    {
        do
        {
            try
            {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String prompt)
    {
        do
        {
            try
            {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readDateFromConsole(String prompt)
    {
        do
        {
            try
            {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex)
            {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List list, String header)
    {
        showList(list,header);
        return selectsObject(list);
    }
    static public int showAndSelectIndex(List list, String header)
    {
        showList(list,header);
        return selectsIndex(list);
    }
    static public void showList(List list, String header)
    {
        System.out.println(header);

        int index = 0;
        for (Object o : list)
        {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 - Cancel");
    }

    static public Object selectsObject(List list)
    {
        String input;
        Integer value;
        do
        {
            input = Utils.readLineFromConsole("Type your option: ");
            value =  Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0)
        {
            return null;
        } else
        {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list)
    {
        String input;
        Integer value;
        do
        {
            input = Utils.readLineFromConsole("Type your option: ");
            value =  Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    static public int[] convertStringArray(String[] arrayToConvert){
        int [] convertedArray = new int[arrayToConvert.length];

        for(int i= 0; i< convertedArray.length; i++){
            convertedArray[i] = Integer.parseInt(arrayToConvert[i])-1;
        }
        return convertedArray;
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static List<Date> getDaysWithoutSundays (Date date, int histPoints) {

        List<Date> listDateExceptSundays = new ArrayList<>();

        LocalDate dataEscolhida = Utils.convertToLocalDateViaInstant(date);

        for (int i = 0; i < histPoints; i++) {
            dataEscolhida = dataEscolhida.minusDays(1);
            if (dataEscolhida.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                dataEscolhida = dataEscolhida.minusDays(1);
            }
            listDateExceptSundays.add(Utils.convertToDateViaInstant(dataEscolhida));
        }

        return listDateExceptSundays;
    }

    public static List<Date> getDays(Date date, int histPoints){
        List<Date> listDate = new ArrayList<>();

        LocalDate dataEscolhida = Utils.convertToLocalDateViaInstant(date);
        listDate.add(Utils.convertToDateViaInstant(dataEscolhida));
        for (int i = 0; i < histPoints-1; i++) {
            dataEscolhida = dataEscolhida.minusDays(1);
            listDate.add(Utils.convertToDateViaInstant(dataEscolhida));
        }

        return listDate;
    }

    public static boolean verifyIfListsEmpty(List<Test> [] lstAllTestWithResultCovidPositive){

        for (int i=0; i< lstAllTestWithResultCovidPositive.length; i++){
            if (lstAllTestWithResultCovidPositive[i].isEmpty())
                return true;
        }
        return false;
    }
}
