package model;

import java.util.ArrayList;

public class HouseGestion {

	 int curr_pos;
	 HouseModel[] houseList;
	
	public HouseGestion(){
		curr_pos= 0;
		houseList = new HouseModel[20];
		for (int i = 0; i<20;i++){
			houseList[i] = null;
		}
	}
	
	
	public HouseModel[] getHouseList() {
		return houseList;
	}

	public void setHouseList(HouseModel[] houseList) {
		this.houseList = houseList;
	}


	public int getCurr_pos() {
		return curr_pos;
	}


	public void setCurr_pos(int c) {
		curr_pos = c;
	}

	public HouseModel getLastHouse(){
		HouseModel m = new HouseModel();
		curr_pos++;
		System.out.println(curr_pos);
		if (curr_pos < 20)
		{
			if (houseList[curr_pos] != null)
				m= houseList[curr_pos] ;
			else m = houseList[0] ;
		}
		else  m= houseList[0];
		return m;
	}


	public void enregistrerHouse(HouseModel m ){
		int drap=0;
		int j =0;

		while( j <20 && drap ==0 )
		{
			if(houseList[j] == null) drap = j;
			else if(j == 19) drap =j;
			j++;
		}
		for( int g = drap; g>=0 ;g-- ){
			if (g != 19) houseList[g+1] =houseList[g];
			else houseList[19] = houseList[18];
		}
		
		//HouseModel mCopy = new HouseModel();
		//ArrayList<RoomModel> o= HouseModel.getPieceList();
		//mCopy.setPieceList(o);
		houseList[0] =m.clone();
	
		System.out.println("**************");
		/*for(int a = 0; a <20 ;a++ ){
			if (houseList[a] != null )
			{
				System.out.println("------------------");
				if (houseList[a].getFirstPiece() != null )
					//System.out.println(houseList[a].getPieceWhereNom("a").getTemperature());
			}
		}
*/
		curr_pos =0;

	}
}
