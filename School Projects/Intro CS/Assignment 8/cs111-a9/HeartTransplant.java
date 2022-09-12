/**
 * 
 * HeartTransplant class
 * 
 * @author Ana Paula Centeno
 * @author Haolin (Daniel) Jin
 */
public class HeartTransplant {

    // patient array, each Patient is read from the data file
    private Patient[] patients;

    // SurvivabilityByAge array, each rate is read from data file
    private SurvivabilityByAge survivabilityByAge;

    // SurvivabilityByCause array, each rate is read from data file
    private SurvivabilityByCause survivabilityByCause;

    /*
     * Default constructor
     * Initializes patients to null.
     * Initializes survivabilityByAge to null.
     * Initializes survivabilityByCause to null. 
     */
    public HeartTransplant() {
        this.survivabilityByAge = null;
        this.survivabilityByCause = null;
        this.patients = null;
        // WRITE YOUR CODE HERE
    }

    /*
     * Returns patients
     */
    public Patient[] getPatients() {

        return this.patients;
     } 

    /*
     * Returns survivabilityByAge
     */
    public SurvivabilityByAge getSurvivabilityByAge() {
        // WRITE YOUR CODE HERE
        return this.survivabilityByAge;
    }

    /*
     * Returns survivabilityByCause
     */
    public SurvivabilityByCause getSurvivabilityByCause() {
        // WRITE YOUR CODE HERE
        return this.survivabilityByCause;
    }

    /*
     * 1) Initialize the instance variable patients array with numberOfLines length.
     * 
     * 2) Reads from the command line data file, use StdIn.readInt() to read an integer.
     *    File Format: 
     *      ID, ethnicity, Gender, Age, Cause, Urgency, State of health
     * 
     *    Each line refers to one Patient, all values are integers.
     * 
     */
    public void readPatients (int numberOfLines) {
        // WRITE YOUR CODE HERE
        this.patients = new Patient[numberOfLines];
        
        for (int i = 0; i < patients.length; i++){
            int id = StdIn.readInt();
            int ethnicity = StdIn.readInt();
            int gender = StdIn.readInt();
            int age = StdIn.readInt();
            int cause = StdIn.readInt();
            int urgency = StdIn.readInt();
            int state = StdIn.readInt();
            patients[i] = new Patient(id, ethnicity, gender, age, cause, urgency, state);
        }
    }

    /*
     * 1) Initialize the instance variable survivabilityByAge with a new survivabilityByAge object.
     * 
     * 2) Reads from the command line file to populate the object. 
     *    Use StdIn.readInt() to read an integer and StdIn.readDouble() to read a double.
     * 
     *    File Format: Age YearsPostTransplant Rate
     *    Each line refers to one survivability rate by age.
     * 
     */
    public void readSurvivabilityByAge (int numberOfLines) {
        // WRITE YOUR CODE HERE
        this.survivabilityByAge = new SurvivabilityByAge();
        
        
        for (int i = 0; i < numberOfLines; i++){
            int age = StdIn.readInt();
            int yearsPostTransplant = StdIn.readInt();
            double rate = StdIn.readDouble();
            this.survivabilityByAge.addData(age, yearsPostTransplant, rate);
        }
    }

    /*
     * 1) Initialize the instance variable survivabilityByCause with a new survivabilityByCause object.
     * 
     * 2) Reads from the command line file to populate the object. Use StdIn.readInt() to read an 
     *    integer and StdIn.readDouble() to read a double.
     * 
     *    File Format: Cause YearsPostTransplant Rate
     *    Each line refers to one survivability rate by cause.
     * 
     */
    public void readSurvivabilityByCause (int numberOfLines) {
        // WRITE YOUR CODE HERE

        this.survivabilityByCause = new SurvivabilityByCause();
        
        
        for (int i = 0; i < numberOfLines; i++){
            int cause = StdIn.readInt();
            int yearsPostTransplant = StdIn.readInt();
            double rate = StdIn.readDouble();
            this.survivabilityByCause.addData(cause, yearsPostTransplant, rate);
    }
    }
    
    /*
     * Returns a Patient array containing the patients, 
     * from the patients array, that have age above the parameter age.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of Patients with age above the parameter age.
     * 
     * Return null if there is no Patient with age above the 
     * parameter age.
     */ 
    public Patient[] getPatientsWithAgeAbove(int age) {
        // WRITE YOUR CODE HERE
        int patientAge = 0;
        int count = 0;

        for(int i = 0; i < patients.length; i++){
            patientAge = this.patients[i].getAge();
            if (patientAge >= age){
                count++;
            }
        }

        Patient array [] = new Patient[count];
        int index = 0;

        for(int i = 0; i < patients.length; i++){
            patientAge = this.patients[i].getAge();
            if (patientAge >= age){
                index++;
                array[index - 1] = patients[index - 1];
            }
        }


        if (count >= 1){
        return array;
        }

        else{
            return null;
        }
    }

    /*
     * Returns a Patient array containing the patients, from the patients array, 
     * that have the heart condition cause equal to the parameter cause.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of Patients with the heart condition cause equal to the parameter cause.
     * 
     * Return null if there is no Patient with the heart condition cause 
     * equal to the parameter cause.
     */ 
    public Patient[] getPatientsByHeartConditionCause(int cause) {

        // WRITE YOUR CODE HERE
        int patientCause = 0;
        int count = 0;

        for(int i = 0; i < patients.length; i++){
            patientCause = this.patients[i].getCause();
            if (patientCause == cause){
                count++;
            }
        }

        Patient array [] = new Patient[count];
        int index = 0;

        for(int i = 0; i < patients.length; i++){
            patientCause = this.patients[i].getCause();
            if (patientCause == cause){
                index++;
                array [index - 1] = patients[index - 1];
            }
        }

        if (count >= 1){
        return array;
        }

        else{
            return null;
        }
    }

    /*
     * Returns a Patient array containing patients, from the patients array,
     * that have the state of health equal to the parameter state.
     * 
     * The return array has to be completely full with no empty
     * spots, that is the array size should be equal to the number
     * of Patients with the state of health equal to the parameter state.
     * 
     * Return null if there is no Patient with the state of health 
     * equal to the parameter state.
     */ 
    public Patient[] getPatientsByUrgency(int urgency) {

        // WRITE YOUR CODE HERE

        int patientUrgency = 0;
        int count = 0;

        for(int i = 0; i < patients.length; i++){
            patientUrgency = this.patients[i].getUrgency();
            if (patientUrgency == urgency){
                count++;
            }
        }

        Patient array [] = new Patient[count];
        int index = 0;

        for(int i = 0; i < patients.length; i++){
            patientUrgency = this.patients[i].getUrgency();
            if (patientUrgency == urgency){
                index++;
                array [index - 1] = patients[index - 1];
            }
        }


        if (count >= 1){
        return array;
        }

        else{
            return null;
        }
	
    }

    /*
     * Assume there is a heart available for transplantation surgery.
     * Also assume that the heart is of the same blood type as the
     * Patients on the patients array.
     * This method finds the Patient to be the recepient of this
     * heart.
     * 
     * The method returns a Patient from the patients array with
     * he highest potential for survivability after the transplant.
     * 
     * Assume the patient returned by this method will receive a heart,
     * therefore the Patient will no longer need a heart.
     * 
     * There is no correct solution, you may come up with any 
     * function to find the patient with the highest potential 
     * for survivability after the transplant.
     */ 
    public Patient getPatientForTransplant () {

	// WRITE YOUR CODE HERE

    int count = 0;
    int index = 0;
    int lowestAge = 18;
    

    for (int i = 0; i < patients.length; i++){




        boolean age = false;
        boolean health = false;
        boolean urgency = false;
        

        this.patients[i].setNeedHeart(false);

        if (this.patients[i].getAge() <= 35){
            age = true;
            if (this.patients[i].getAge() < lowestAge){
            lowestAge = this.patients[i].getAge();
            index = i;
            }
        }

        if (this.patients[i].getStateOfHealth() == 7){
            health = true;
        }

        if (this.patients[i].getUrgency() == 8){
            urgency = true;
        }

        if (age == true && health == true && urgency == true){
            count++;
        }

    }

    if (count >= 1){
        this.patients[index - 1].setNeedHeart(true);
    }

   // Patient subject = this.patients[index - 1];
	return this.patients[index - 1];
    }
}
