import java.io.*; 

public class carRegoreaderwriter {
 public static String carregfile = "/home/emmanuel/java-tutorial/car_rego.csv";//change the location of the file for car registration 
 public static String carspecfile= "/home/emmanuel/java-tutorial/car_specs.csv";//change the location of the file for car specs
 public static BufferedReader br, bb; 
 public static String csvDelimit = ","; 
 public static boolean found = false;
 public static String ll, lb, x, y, ma, mo, yr;

/***********************************************************************************************
carReader method is used to print the make model and year of the car using the car registration
Usage: carReader("Registration Id") 
Example: carReader("ABC130")
************************************************************************************************/

	public static void carReader(String regId) {
		try {
			br = new BufferedReader(new FileReader(carregfile)); 
			bb = new BufferedReader(new FileReader(carspecfile));
			while (((ll = br.readLine()) != null) && !found)  {//Read every line of the car_rego.csv file to the variable ll 
				String[] carRego = ll.split(csvDelimit); 
			       if (carRego[0].equals(regId)) { //match the line that contains the car registration id to get the car id
					found = true;
					x = carRego[1];
					y = carRego[0];
					break;
					}
			        }
			found = false;
			
			while (((lb = bb.readLine()) != null) && !found)  { // Read every line of the car_spec.csv file to the variable lb
                                String[] carSpec = lb.split(csvDelimit); // match the line for the make model year using car id
				if (x.equals(carSpec[0])) {
					found = true;
					ma = carSpec[13];
					mo = carSpec[14];
					yr = carSpec[15];
					break;	
					}
				}
			System.out.println("RegId:" + y + " Make:" + ma + " Model:" + mo + " Year:" + yr);
			br.close();
			bb.close();

		     }
		catch (FileNotFoundException e) { 
			e.printStackTrace(); 
			}
		catch (IOException e) { 
			e.printStackTrace(); 
			}
	}


/* *******************************************************************************************************
carReader is a method to print the registration id of a car using the make model and year of the car.
Usage: carReader("make","model","year")
Example: carReader("Volkswagen","Jetta","2012")
***********************************************************************************************************/

        public static void carReader(String ma, String mo, String yr) {
                try {
                        br = new BufferedReader(new FileReader(carregfile)); 
                        bb = new BufferedReader(new FileReader(carspecfile));
                        while (((lb = bb.readLine()) != null) && !found)  { //Read every line of the car_spec.csv file to the variable lb
                                String[] carSpec = lb.split(csvDelimit); 
                               if ((carSpec[13].equals(ma)) && (carSpec[14].equals(mo)) && (carSpec[15].equals(yr))) { //match the line for car id using the make model year 
                                        found = true;
                                        x = carSpec[0];
                                        break;
                                        }
                                }
                        found = false;

                        while (((ll = br.readLine()) != null) && !found)  { //Read every line of the car_rego.csv file to the variable ll
                                String[] carRego = ll.split(csvDelimit);
                                if (x.equals(carRego[1])) { //Match the line using the car id for the car registration id
                                        found = true;
					y = carRego[0];
                                        break;
                                        }
                                }
                        System.out.println("RegId:" + y + " Make:" + ma + " Model:" + mo + " Year:" + yr);
                        br.close();
                        bb.close();

                     }
                catch (FileNotFoundException e) { 
                        e.printStackTrace(); 
                        }
                catch (IOException e) {
                        e.printStackTrace(); 
                        }
        }

/******************************************************************************************************** 
AddcarReg is a method to add car registration id to the last line of the existing csv file (car_rego.csv)
Usage: AddcarReg("Registration ID")
Example: AddcarReg("WHE123")
*********************************************************************************************************/
        public static void AddcarReg(String y) {
                try {
			int a = 0;
			String last = "";
			br = new BufferedReader(new FileReader(carregfile));
			while ((ll = br.readLine()) != null) {
			String[] carRego = ll.split(csvDelimit);
			last = carRego[1]; // get the last line in the car_rego.csv to work out the next car id
			}
			br.close();
			a = Integer.parseInt(last); 
			a = a + 1;
			String b = Integer.toString(a);
			String lal = y + "," + b;
			//FileWriter writer = new FileWriter(carregfile,true);
			BufferedWriter bw = new BufferedWriter(new FileWriter(carregfile,true)); // Open the file car_rego.csv to buffer for appending the new line
			bw.write(lal); // Write the string to the file car_rego.csv
			System.out.println("Reg:" + y + "has been added. CarId:" + b);
			bw.close();
                     }
	         catch (FileNotFoundException e) {
                        e.printStackTrace();
                        }
                 catch (IOException e) {
                        e.printStackTrace();
                        }
              }

}
