#include <iostream>
#include <fstream>
#include <string>
 
class LetterFreq{
    private:
        char letter;
        int freq;
    public:
LetterFreq(char c){
    letter = c;
    freq = 1;
}
char getChar(){
    return letter;
}
void setFreq(int z){
    freq = z;
}
int getFreq(){
    return freq;
}
};
 
std::string PickWord (int z){
    std::ifstream answers("answers.txt");
    srand((unsigned int)time(NULL));
    int randomWordNum = ((rand() % z) + 1);
    std::string trash = "no";
    for (int i = 0; i < randomWordNum; i++){
        std::getline(answers, trash);
    }
    std::string word = "yes";
    std::getline(answers, word);
    return word;
}
 
void GuessCheck(int z, std::string w, LetterFreq a[]){
    LetterFreq letter1('0');
    LetterFreq arr[5] = { letter1, letter1, letter1, letter1, letter1 };
    for (int i = 0; i < 5; i++){
        arr[i] = a[i];
    }
    std::string guess = "guess";
    std::cin >> guess;
 
    std::string key = "-----";
 
    //Greens first
    for (int i = 0; i < 5; i++){
        if (guess[i] == w[i]){
            key[i] = '^';
            for (int j = 0; j < 5; j++){
                if (guess[i] == arr[j].getChar()){
                    arr[j].setFreq(arr[j].getFreq() - 1);
                    break;
                }
            }
        }
       
    }
 
    for(int i = 0; i < 5; i++){
        for(int j = 0; j < 5; j++){
            if (guess[i] == w[j] && guess[i] != w[i]){
                for(int k = 0; k < 5; k++){
                    if(arr[k].getChar() == guess[i] && arr[k].getFreq() > 0){
                        key[i] = '!';
                        arr[k].setFreq(arr[k].getFreq() - 1);
                        break;
                    }
                }
                }
            }
       
        }
   
 
   
std::cout << key << std::endl;
if (key == "^^^^^"){
    std::cout << "You Win!" << std::endl;
    std::string h = " ";
    std::cout<<"\n\nEnter anything to exit: ";
    std::cin>>h;
    exit(0);
}
 
}
 
int main(){
    std::ifstream answers("answers.txt");
    std::string word = "bob";
    std::getline(answers, word);
    int lineTotal = stoi(word);
    word = PickWord(lineTotal);
 
    std::cout<< "Welcome to Wordle!" << std::endl;
    std::cout<< "Incorrect guesses will be represented as a -" << std::endl;
    std::cout<< "Correct guesses in the wrong spot will be represented as a !" << std::endl;
    std::cout<< "Correct guesses will be marked with a ^" << std::endl;
    std::cout<< "You will get six guesses, each one being 5 letters\n" << std::endl;
    std::cout<< "Please enter your first guess:" << std::endl;
 
    
    LetterFreq letter1('0');
    LetterFreq freqArray[5] = { letter1, letter1, letter1, letter1, letter1 };
   
    for (int i = 0; i < 5; i++){
        for (int j = 0; j < 5; j++){
            if (freqArray[j].getChar() == '0'){
                LetterFreq n(word[i]);
                freqArray[j] = n;
                break;
            }
            else if(word[i] == freqArray[j].getChar()){
                freqArray[j].setFreq(freqArray[j].getFreq() + 1);
                break;
            }
        }
    }
   
    
   
   
 
 
    GuessCheck(1, word, freqArray);
    GuessCheck(2, word, freqArray);
    GuessCheck(3, word, freqArray);
    GuessCheck(4, word, freqArray);
    GuessCheck(5, word, freqArray);
    GuessCheck(6, word, freqArray);
    std::cout << "You lose! The word was " << word << std::endl;
 

std::string h = " ";
    std::cout<<"\n\nEnter anything to exit: ";
    std::cin>>h;

    return 0;
}

