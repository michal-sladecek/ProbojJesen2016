#include <iostream>
#include <string>
#include <algorithm>
#include <sstream>

#include "common.h"

using namespace std;

int ja = -1;

const double STRACH = 2;
const double AGRESIVITA = 10;
const double ODSEBA = -100;
const double KAMEN = 5;
const double SPEED = 5;

int main() {
    srand(time(NULL));

    string str;
    cin >> str >> ja;

 //   cerr << ja << endl;

    game_state gs;

    while (true) {
		nacitaj(cin, gs);
		vector<vector<double> > ohodnotenePolicka(gs.width + 2, vector<double> (gs.height + 2, 0));
		int currentLength = 0;

		int x = gs.players[ja].position.x;
		int y = gs.players[ja].position.y;
		vector<pair<int,int> > navnady;
		if(navnady.size() <= NUM_NAVNAD){
			
		}
		for (int x = 0; x < gs.width; x++) {
			for (int y = 0; y < gs.height; y++) {
				char c = '.';
				if (gs.get_block(x, y).type == WALL) ohodnotenePolicka[x + 1][y + 1] = -1;
				else if (gs.get_block(x, y).crossed_by == ja){
					ohodnotenePolicka[x + 1][y + 1] += ODSEBA;
					++currentLength;
				}
				else if (gs.get_block(x, y).crossed_by != -1) ohodnotenePolicka[x + 1][y + 1] += AGRESIVITA;
				else ohodnotenePolicka[x + 1][y + 1] = 10;
			}
		}
		for (int x = 0; x < gs.width; x++) {
			for (int y = 0; y < gs.height; y++) {
				if (gs.get_block(x, y).owned_by == ja) ohodnotenePolicka[x + 1][y + 1] += currentLength*STRACH;
			}
		}

		for(auto p: gs.players){
			ohodnotenePolicka[p.position.x + 1][p.position.y + 1] += -STRACH*currentLength*10;
		}

		for(auto p: ohodnotenePolicka){
			for (auto q: p){
				cerr << q << " ";
			}
			cerr << "\n";
		}
		cerr << "--------------------------\n";
		vector<string> dirs;
		if (x > 0 && gs.blocks[gs.block_index({x - 1, y})].crossed_by != ja) dirs.push_back("LEFT");
		if (x < gs.width - 1 && gs.blocks[gs.block_index({x + 1, y})].crossed_by != ja) dirs.push_back("RIGHT");
		if (y > 0 && gs.blocks[gs.block_index({x, y - 1})].crossed_by != ja) dirs.push_back("UP");
		if (y < gs.height - 1 && gs.blocks[gs.block_index({x, y + 1})].crossed_by != ja) dirs.push_back("DOWN");

		if (dirs.size() > 0) {
			cout << "cd " << dirs[rand() % dirs.size()] << endl;
		}
    }
}
