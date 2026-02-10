package com.example;

// Imports
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Define a flag to control the user inputs loop and the variables to be used.
        boolean flag = true;
        int choice, formatFlag;
        double input, output;
        String inputType, outputType;
        // Define a list of the supported currency types, includes cryptocurrencies and metals/bonds.
        List<String> currencyTypes = List.of( "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN",
                                                  "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL",
                                                  "BSD", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLP",
                                                  "CNY", "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP",
                                                  "DZD", "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL",
                                                  "GHS", "GIP", "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG",
                                                  "HUF", "IDR", "ILS", "INR", "IQD", "IRR",  "ISK",  "JMD",
                                                  "JOD",  "JPY" ,  "KES" ,  "KGS" ,  "KHR" ,  "KMF" ,  "KRW" , "KWD" ,  
                                                  "KYD" ,  "KZT" ,  "LAK" ,  "LBP" ,  "LKR" ,  "LRD" ,  "LSL" ,  "LYD" ,
                                                  "MAD" ,  "MDL" ,  "MGA" ,  "MKD" ,  "MMK" ,  "MNT" ,  "MOP" ,  "MRU" ,  
                                                  "MUR" , "MVR" ,  "MWK" ,  "MXN" ,  "MYR" ,  "MZN" ,  "NAD" ,  "NGN" ,  
                                                  "NIO" ,  "NOK" , "NPR" ,  "NZD" ,  "OMR" ,  "PAB" ,  "PEN" ,  "PTE", 
                                                  "PGK" , "PHP" ,  "PKR" ,  "PLN" , "PYG" ,  "QAR" ,  "RON" ,  "RSD" ,  
                                                  "RUB" , "RWF" ,  "SAR" ,  "SBD" ,  "SCR" , "SDG" ,  "SEK" ,  "SGD" ,  
                                                  "SHP" , "SLL" ,  "SOS" ,  "SRD" ,  "SSP" ,  "STN" , "SVC" ,  "SYP" , 
                                                  "SZL" , "THB" ,  "TJS" ,  "TMT" ,  "TND" ,  "TOP" ,  "TRY" , "TTD" ,  
                                                  "TWD" ,  "TZS" ,  "UAH" ,  "UGX" ,  "USD" ,  "UYU" ,  "UZS" , 
                                                  "VES" , "VND" ,  "VUV" ,  "WST" ,  "XAF" ,  "XCD" ,  "XDR" ,  "XOF" ,  
                                                  "XPF" , "YER" ,  "ZAR" ,  "ZMW" , "ZWL",
                                                  // Metals and Bonds: 
                                                  "XAG", "XAU", "XBA", "XBB", "XBC", "XBD", "XPT", "XPD", "XSU",
                                                  // Cryptocurrencies to be futurely added as the apis arent relaible:
                                                  "BTC", "ETH", "BNB", "ADA", "XRP", "SOL", "AVAX"
                                                  );

        // Create an instance of the CurrencyConverter class to use its methods.
        CurrencyConverter converter = new CurrencyConverter();

        // User interaction loop.
        while(true){
            System.out.println("\n===============================================================");
            System.out.println("Welcome to the Currency Converter! \nPlease use the 3 character code for each currency type.\nOptions: \n1. Check the supported currency and their codes \n2. Convert an amount \n3. Exit the application");
            choice = Integer.parseInt(System.console().readLine());
            switch(choice){
                case 1:
                    System.out.println("The supported currency types and their codes are:");
                    // Just a auxiliary variable to format the output in "collumns".
                    formatFlag = 1;
                    for(String currency : currencyTypes){
                        System.out.printf("%-4s- %-41s||", currency, converter.getCurrencyName(currency));
                        // Format the output to show 3 currencies per line.
                        if(formatFlag % 3 == 0){
                            System.out.print("\n");
                        }
                        formatFlag++;
                    }
                    break;
                case 2:
                    do{
                        System.out.println("Enter the amount you want to convert:");
                        input = Double.parseDouble(System.console().readLine());
                    }while(!flag);
                    do{
                        System.out.println("Enter the input currency type:");
                        inputType = System.console().readLine();
                        // Check if the input is supported, not case sensitive.
                        flag = currencyTypes.contains(inputType.toUpperCase());
                        if(!flag){
                            System.out.println("Invalid currency type. Please try again.");
                        }
                    }while(!flag);
                    do{
                        System.out.println("Enter the output currency type:");
                        outputType = System.console().readLine();
                        // Check if the input is supported, not case sensitive.
                        flag = currencyTypes.contains(outputType.toUpperCase());
                        if(!flag){
                            System.out.println("Invalid currency type. Please try again.");
                        }
                    }while(!flag);
                    do{
                        // Make the conversion and return the result to the user.
                        output = converter.convert(input, inputType, outputType);
                        System.out.println("The converted amount is: " + output + " " + outputType.toUpperCase());
                    }while(!flag);
                    break;
                case 3:
                    System.out.println("Thank you for using the Currency Converter! Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }    
}

