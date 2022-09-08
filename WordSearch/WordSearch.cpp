#include <iostream>
#include <string>
#include <cstdlib>
#include <ctime>
#include <vector>
 
std::vector<std::vector<char>> InsertWord(int h, std::vector<std::vector<char>> g){
   
    std::string word1;
    std::cin>>word1;
    bool fits = false;
    int z = word1.length();
    while (fits == false){
        int xDir = ((rand()%3)+1) - 2;
        int yDir = ((rand()%3)+1) - 2;
        int xStart = ((rand()%h)+1) - 1;
        int yStart = ((rand()%h)+1) - 1;
       
        if (!(xDir == 0 && yDir == 0)){
            bool allowed = true;
            if(xStart + z*xDir > h - 1 || xStart + z*xDir < 0 || yStart + z*yDir > h - 1 || yStart + z*yDir < 0){
            allowed = false;
            }
        else{      
            for(int i = 0; i < z; i++){
                if((g[xStart + xDir*i][yStart + yDir*i] != ' ') && (g[xStart + xDir*i][yStart + yDir*i] != word1[i])){
                    allowed = false;
                    break;
                }
            }
        }
        if(allowed  == true){
            fits = true;
            for(int i = 0;i < z;i++){
                g[xStart + xDir*i][yStart + yDir*i] = word1[i];
            }
        }
    }
    }
    return g;
}
 
int main(){
    std::cout<<"Please enter a grid size:"<<std::endl;
    std::string size;
    std::cin>>size;
    int gridSize = stoi(size);
 
    std::cout<<"\nHow many words would you like to use?"<<std::endl;
 
    std::string wCount;
    std::cin>>wCount;
    int wordCount   = stoi(wCount);
   
    srand( (unsigned)time(NULL) );
    std::vector<std::vector<char>> grid(gridSize, std::vector<char> (gridSize, ' '));
    std::cout<<"\nPlease enter " << wordCount << " words with " << (gridSize*3)/4 << " charcters or less: "<<std::endl;
 
    for(int i = 0; i < wordCount; i++){
        grid = InsertWord(gridSize, grid);
    }
   
    std::vector<std::vector<char>> answers(gridSize, std::vector<char> (gridSize));
    answers = grid;
    std::cout<<"\n\n";
   
    for(int i = 0; i < gridSize; i++){
        for(int j = 0; j < gridSize; j++){
            if (grid[i][j] == ' '){
                int num = ((rand()%26) + 1) + 96;
                grid[i][j] = (char)num;
            }
            std::cout<<grid[i][j]<<' ';
        }
        std::cout<<"\n";
    }
    std::string q = " ";
    std::cout<<"\n\nEnter anything to see the answer key: \n";
    std::cin>>q;
    std::cout<<"\n";
 
    for(int i = 0; i < gridSize; i++){
        for(int j = 0; j < gridSize; j++){
            std::cout<<answers[i][j]<<' ';
        }
        std::cout<<"\n";
    }
    std::string h = " ";
    std::cout<<"\n\nEnter anything to exit: ";
    std::cin>>h;
}

