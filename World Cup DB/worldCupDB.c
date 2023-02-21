/**
 * Hirbod Hosseini
 * Sunday Nov 6th, 2022
 * Description: A program that is meant to act as a database for teams participating in the 2022 World Cup
 * Purpose: To demonstrate knowledge of basic programming concepts in c such as strings, structures and scope
 * */

// library imports
# include <stdio.h>
# include <string.h>

// Team structure
struct Team {
    // structure members
    int code;
    char colour;
    char seed[3];
    char name[100];
};

// helper method to provide string literals for char values of colours
const char* findColor(struct Team t){
    if(t.colour == 'R')
        return "Red";
    if (t.colour == 'B')
        return "Blue";
    if (t.colour == 'O')
        return "Orange";
    if (t.colour == 'Y')
        return "Yellow";
    if (t.colour == 'G')
        return "Green";
    if (t.colour == 'I')
        return "Indigo";
    return "Violet";
}

// prints team info
void displayTeam(struct Team arg){
    // left justified formatting
    printf("%-13d%-26s%-20s%-10s\n", arg.code, arg.name, arg.seed, findColor(arg));
}

// duplicate team code checker method 
// returns 0 if no duplicates, 1 otherwise
int duplicateCode(struct Team teams[], int c){
    // looping through teams database
    for (int i = 0; i <32; i++){
        // checking for matching team codes
        if (teams[i].code == c){
            // error message
            printf("Duplicate team codes error\n");
            return 1;
        }
    }
    return 0;
}

// duplicate team name checker method. returns 0 if no duplicates, 1 otherwise
int duplicateName(struct Team teams[], char str[]){
    // looping through teams database
    for (int i = 0; i < 32; i++){
        // checking for matching team names
        if (strcmp(teams[i].name, str)==0){
            // error message
            printf("Duplicate team names error\n");
            return 1;
        }
    }
    return 0;
}

// duplicate team seeds checker method. returns 0 if no duplicates, 1 otherwise
int duplicateSeed(struct Team teams[], char str[]){
    // looping through teams database
    for (int i = 0; i < 32; i++){
        // looking for team with matching seed
        if (strcmp(teams[i].seed, str)==0){
            // eroor message
            printf("duplicate team seeds error\n");
            return 1;
        }
    }
    return 0;
}


int main(void){
    // teams array
    struct Team teams[32];
    // empty/null team object for intialization and comparison
    struct Team ini;
    // setting team code to invalid value for comparison
    ini.code = -1;
    // declaring and initializing index variable for teams array
    int index = 0;
    // infinite while loop condition/flag
    int infiniteFlag = 1;
    // input variable declaration
    char inp;
    while(infiniteFlag){
        // redundantly reinitializing empty items in teams array at each iteration to avoid errors
        for (int i = index; i<32; i++)
            teams[i]=ini;
        // header print statement
        printf("\n\n******************\n* 2211 World Cup *\n******************\n\n");
        // seeking and storinginput
        printf("Enter i to insert a team\nEnter s to search for a team\nEnter u to update a team\nEnter p to view all teams\nEnter q to quit\nEnter operation code: ");
        scanf(" %c", &inp);
        // declaring variables to store input
        int c = 0, i =0;
        char str[100];
        char col;
        char seed[3];
        // interperting input
        // insert case
        if (inp == 'i'){
            // checking for space in database
            if (index>31){
                printf("Database is full\n");
                continue;
            }
            // seeking and storing team code input
            printf("\tEnter team code in range 0-31(duplicates not allowed): ");
            // ignoring spaces when looking for input 
            // this is done frequently in the program
            scanf(" %d", &c);
            // checking for valid team code input
            if (c<0||c>31||duplicateCode(teams, c)){
                printf("Invalid team code error\n"); 
                continue;
            }
            // seeking and storing team name input
            printf("\tEnter team name: ");
            // multiple scanf modifers to allow for spaces in team names
            // scanf with modifiers to allow for spaces inside string input
            scanf(" %[^\n]s", str);
            // fixing input to appropriate length
            if(str[24]!='\0')
                str[24]='\0';
            // checking for duplicate team names
            if(duplicateName(teams, str)){
                printf("Duplicate team names\n");
                continue;
            }
            // seeking and storing seed input
            printf("\tEnter group seeding in such format(A1-H4): ");
            scanf(" %s", seed);
            // checking for valid seed input
            if (duplicateSeed(teams, seed) || seed[0]<'A' || seed[0]>'H' || seed[1]<'0' || seed[1] >'4'){
                printf("Invalid seed input error\n");
                printf("\n\n\n\n");
                continue;
            }
            // seeking and storing kit colour input
            printf("\tEnter the kit colour in such format('R' for red, 'Y' for yellow): ");
            scanf(" %c", &col);
            // checking for valid kit colour input
            if (col != 'R' && col != 'O' && col != 'Y' && col != 'G' && col != 'B' && col != 'I' && col != 'V'){
                printf("Invalid kit  colour input\nMust be of form 'Z' where Z is a char corresponding to a colour\n");
                printf("\n\n\n\n");
                continue;
            }
            // initializing and storing team object in teams array
            struct Team team = ini;
            strcpy(team.name, str);
            team.code = c;
            team.colour = col;
            strcpy(team.seed, seed);
            teams[index] = team;
            // incrementing index
            index++;
        }
        // search case
        if (inp=='s'){
            // seeking and storing team code input
            printf("\tEnter team code: ");
            scanf("%d", &c);
            // checking for valid team code input
            if (c<0||c>31){
                printf("Team code out of range\n");
                continue;
            }
            // team pointer pointing to comparison team object
            struct Team t = ini;
            // looping through teams array to find team with matching code
            for (int i = 0; i<32; i++){
                // user t to point to found team object
                if (teams[i].code==c)
                    t = teams[i];
            }
            // case where team with passed code was stored in database
            if (t.code!=-1){
                // printing header
                printf("%-13s%-26s%-20s%-10s\n", "Team Code", "Team Name", "Group Seeding", "Primary Kit Colour");
                // printing team info
                displayTeam(t);
            }
            // case where teach with passed code was not stored in database
            else{
                printf("Team code not found\n");
            }
        }
        // update case
        if (inp=='u'){
            // flag for missing team (team with code not already in database)
            int teamNotFound = 1;
            // flag for invalid input
            int invalidSeedInput = 0;
            // seeking and stroring team code input
            printf("\tEnter team code: ");
            scanf(" %d", &c);
            // pointing to null team object
            struct Team t = ini;
            // looping through teams array
            for (int i = 0; i<32; i++){
                // checking for matching team codes
                if (teams[i].code==c){
                    // lowering missing team flag since team with matching code was found
                    teamNotFound = 0;
                    // seeking and storing input for update values
                    printf("\tEnter team name: ");
                    scanf(" %[^\n]s", str);
                    printf("\tEnter group seeding in such format (A1-H4): ");
                    scanf(" %s", seed);
                    // checking for valid seed value
                    if (duplicateSeed(teams, seed) || seed[0]<'A' || seed[0]>'H' || seed[1]<'0' || seed[1] >'4'){
                        printf("Invalid seed\n");
                        // raising flag
                        invalidSeedInput = 1;
                        break;
                    }
                    printf("\tEnter the kit colour in such format ('R' for red, 'Y' for yellow): ");
                    scanf(" %c", &col);
                    // storing updated values inside team object with matching code
                    strcpy(teams[i].name, str);
                    strcpy(teams[i].seed, seed);
                    teams[i].colour = col;
                    }
            }
            // case where invalid input was passed
            if (invalidSeedInput)
                continue;
            // case where team with passed code was not in database
            if (teamNotFound)
                printf("Team code not in database :(\n");
        }
        // print case
        if(inp == 'p'){
            // header print statement
            printf("%-13s%-26s%-20s%-10s\n", "Team Code", "Team Name", "Group Seeding", "Primary Kit Colour");
            // printing each team object that has been inserted
            for (int i = 0; i<32; i++){
                // checking for non-empty enteries
                if (teams[i].code!=-1)
                    displayTeam(teams[i]);
            }
        }
        // quit case
        if(inp == 'q')
            // lowering infinite loop flag
            infiniteFlag = 0;
    }
    // exiting program
    return 0;
}