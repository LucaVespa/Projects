#include <iostream>
#include <string>
#include <vector>
#include <cstdlib>


std::vector<std::vector<int>> fillMatrix(int j, std::vector<std::vector<int>> matrix){
	std::string str = " ";
	std::cin>>str;
	for (int i = 0; i < 9; i++){
	//Subtract 48 because if weird ASCII interaction
		matrix[j][i] = int(str[i]) - 48;
	}
	return matrix;
}

std::vector<std::pair<int, int>> getPairList(std::vector<std::vector<int>> matrix){

	std::vector<std::pair<int, int>>pairList(82);
	pairList[0].first = 0;
	pairList[0].second = 0;
	for(int i = 0; i < 9; i++){
        for(int j = 0; j < 9; j++){
        	if (matrix[i][j] == 0){
        		pairList[0].first++;
        		pairList[pairList[0].first].first = i;
        		pairList[pairList[0].first].second = j;
        	}
        }
     }
	return pairList;
}

std::vector<std::vector<int>> fillBox(std::vector<std::vector<int>> matrix,std::vector<std::pair<int, int>> * pairs){
	
	std::vector<std::pair<int, int>>pairList(82);
	pairList = *pairs;
	std::vector<bool>marked(10, false);
	std::vector<bool>markedReset(10, false);
	int markedCount = 0;
	int count = 0;
	
	for(int i = 1; i < pairList[0].first + 1; i++){
		int x = pairList[i].first;
		int y = pairList[i].second;
		int xMod = x/3;
		int yMod = y/3;
		
		for(int j = 0; j < 9; j++){
			if(matrix[x][j] != 0 || j != y){	
				marked[matrix[x][j]] = true;
			}
		}
		for(int j = 0; j < 9; j++){
			if(matrix[j][y] != 0 || j != x){	
				marked[matrix[j][y]] = true;
			}
		}
		for (int j = 0; j < 3; j++){
		   for (int k = 0; k < 3; k++){
		   	if (matrix[j + (3*xMod)][k + (3*yMod)] != 0 || (j + (3*xMod) != x && k + (3*yMod) != y)){
		   		marked[matrix[j + (3*xMod)][k + (3*yMod)]] = true;
		   	}
		   }
		}
		for(int j = 1; j < 10; j++){
			if (marked[j] == false){
				markedCount++;
			}
		}
		if (markedCount == 1){
			for(int j = 1; j < 10; j++){
				if (marked[j] == false){
					matrix[x][y] = j;
					std::pair<int, int>tempPair;
					tempPair = pairList[pairList[0].first];
					pairList[pairList[0].first] = pairList[i];
					pairList[i] = tempPair;
					pairList[0].first = pairList[0].first - 1;
					*pairs = pairList;
					return matrix;
				}
			}
		}
		marked = markedReset;
		markedCount = 0;
	}

	pairList[0].second = 1;
	*pairs = pairList;
	return matrix;
}

int main(){
	std::vector<std::vector<int>> grid(9, std::vector<int> (9, 0));
	
	std::vector<std::pair<int, int>>coords(82);
	std::vector<std::pair<int, int>> * coordsPtr = &coords;
	
    std::cout<<"Enter each line of the sudoku one at a time. Represent empty spaces using the number 0:\n";
    
    for(int i = 0; i < 9; i++){
    	grid = fillMatrix(i, grid);
    }
	
    coords = getPairList(grid);
    
    for(int i = 0; i < 81; i++){
       grid = fillBox(grid, coordsPtr);
       if (coords[0].first == 0){
       	 break;
       }
       
    }
    
    std::cout<<"\n";
 
    for(int i = 0; i < 9; i++){
        for(int j = 0; j < 9; j++){
        	std::cout<<grid[i][j]<<' ';
        	if (j == 2 || j == 5){
        	  std::cout<<"| ";
            }
        
        }
        if (i == 2 || i == 5){
        	std::cout<<"\n- - - - - - - - - - -";
            
        }
        std::cout<<"\n";
     }


	std::string h = " ";
    std::cout<<"\n\nEnter anything to exit: ";
    std::cin>>h;
	return 0;
}

