import java.util.Scanner;

public class Ship {
	
	// class members
	
	  static int grid[][]; //the grid for tracking the ship
	  static int G[][][]; //grid for tracking the "move-off" points
	  int dir=0; //direction
	  static int t=0;
	  static int x=0;
	  static int y=0;
	  static String ship_instruction=" "; //current position of the ship
	  char orientation='\0'; //current orientation of the ship
	  static char instruction[];
	  static int len;
	  int posx=0; //position of the ship
      int posy=0;  //position of the ship
	  static int tempx=0; // temporary variable to check if the ship falls outside the grid
	  static int tempy=0;  // temporary variable to check if the ship falls outside the grid
	  static int blocked_x=-1; // for marking the cell from which the ship is lost once
	  static int blocked_y=-1;
	  static int blocked_dir=-1;
	  static char pre_ori='\0';
	  static int pre_dir=0;
	  
	
		  public Ship(int d,int x,int y,char o)
		  {
		  	posx=x;
		  	posy=y;
		  	dir=d;
		  	orientation=o;	
		  }
		  public Ship(char o,int d)
		  {
		  	orientation=o;
		  	dir=d;
		  }  
		  
		  
		  //methods 
		  
		  // method for setting the direction after getting the orientation
		  public static Ship set_direction(Ship o)
		    {
		    	switch(o.orientation)
		    	{
		    	case 'N':
		    		o.dir=1;
		    		break;
		    	case 'E':
		    		o.dir=0;
		    		break;
		    	case 'S':
		    		o.dir=3;
		    	case 'W':
		    		o.dir=2;
		    	}	
		    	return o;
		    }
		  
		  // method for setting the orientation for a particular direction
		   public static char set_orientation(int dir)
		    {
		    	char orientation='\0';
		    	switch(dir)
		    	{
		    	case 0:
		    	   orientation='E';
		    	    break;
		    	case 1: 
		    	 orientation='N';
		    	 break;
		    	case 2: 
		    		orientation='W';
		    		break;
		    	case 3: 
		    		orientation='S';
		    		break;
		    	}
		    	
		    	return orientation;
		    }
		   
		   // turn left for instruction "L"
		   public static Ship turn_left(Ship o)
			  {
				  o.dir = (o.dir+1)%4;
				  
				  return o;
			  }
		   
		   //turn right for instruction "R"
		   public static Ship turn_right(Ship o)
			  {
				  o.dir=(o.dir+3)%4;
				  return o;
			  }
		   
		   
		   // method for checking the grid boundary
		   public static boolean is_Safe(int tempx,int tempy)
			  {
				  if(tempx<=x && tempy<=y)
				  {
					  return true;
				  }
				  else
					  return false;
				  
			  }
		   
		   //method for checking the "move-off" points
		   public static boolean is_Blocked(Ship o)
			  {
				  if(G[o.posx][o.posy][o.dir]==1)
				  {
					  return true;
				  }
				  
	               else
	            	   return false;	
			  }
			  
		   
		   //method for tracking the movement of the ship
		    public static int move_forward(Ship obj)
		  {
			  int x=-1;
			  switch (obj.dir)
			  {
			    case 0:
				   if(is_Blocked(obj)) // for checking the "move-off" point
				   {
					 x=2;
					 break;
				   }
				   else
				    {
					 tempx=obj.posx+1;
				     tempy=obj.posy;
				         if(is_Safe(tempx,tempy)) //for checking the grid boundary
				          {
					        obj.posx=obj.posx+1;
					        obj.posy=obj.posy;
					        x=1;
				          }
				         else
				         {
					        x=0;
				         }
				     }
				  break;
			    case 1: 
				   if(is_Blocked(obj)) // for checking the "move-off" point
				    {
					   x=2;
				       break;
				    }
				   else
				    {
				      tempx=obj.posx;
			          tempy=obj.posy+1;
			             if(is_Safe(tempx,tempy)) //for checking the grid boundary
			               {
			    	         obj.posy=obj.posy+1;
					         obj.posx=obj.posx;
					         x=1;
			               }
			               else
			                {
			            	   x=0;
			                }
				    }
				   break;
			    case 2: 
			    	if(is_Blocked(obj)) // for checking the "move-off" point
			    	{
			    		x=2;
			    		break;
			    	}
			    	else
			    	{
			    		tempx=obj.posx-1;
			    		tempy=obj.posy; 
			    			if(is_Safe(tempx,tempy)) //for checking the grid boundary
			    			 {
			    			   obj.posx=obj.posx-1;
			    			   obj.posy=obj.posy;
			    			   x=1;
			    			 }
			    			else
			    			{
			    				x=0;
			    			}
			    	} 
			    	break;
			    case 3:
				  if(is_Blocked(obj)) // for checking the "move-off" point
				  	{
					     x=2;
						 break;
				  	}
				  else
				   {
				     tempx=obj.posx;
				     tempy=obj.posy-1;
				        if(is_Safe(tempx,tempy)) //for checking the grid boundary
				          {
					        obj.posy=obj.posy-1;
					        obj.posx=obj.posx;  
					        x=1;
				          }
				        else
				        {
					     x=0;
				        }
				   }
				 break;	  
			   }
			return x; 
			
		  }
		  
		  
		  public static void main (String[] args) {
				
					Scanner sc=new Scanner(System.in); // for getting user input
					int x1=0;
					int y1=0;
					char ori='\0';
					int result=-1;
					
					//number of ships
					System.out.println("please enter the number of ships you want to track");
					int ship=sc.nextInt();
					
					// Grid coordinates
					 System.out.println("Please enter the top right coordinates of the rectangular world");
				     x=sc.nextInt();
				     y=sc.nextInt();
				     
				     //grid initialization
				     grid=new int[x+1][y+1];
				     G=new int[x+1][y+1][4];
				     
				     // while loop for the tracking the number of ships
					 while(t<ship)
					  {
						t++;
						
						// Number of ships and orientation
						System.out.println("Please enter the current position followed by the orienation of the ship");
						x1=sc.nextInt();
						y1=sc.nextInt();
						ori=sc.next().charAt(0); 
						
						// Ship instruction
						System.out.println("Please enter the sequence of ship positions");
						sc.nextLine();
						ship_instruction=sc.nextLine();
						len=ship_instruction.length();
						instruction=ship_instruction.toCharArray();
						
						Ship obj=new Ship(ori,x1,y1,ori);
						obj=set_direction(obj); // for setting the direction
						
					    char out_orientation='\0';
						for(int i=0;i<len;i++)
						{
							if(instruction[i]=='L')
							{   
								obj=turn_left(obj);
								
							}
							else if (instruction[i]=='R')
							{
								
								obj=turn_right(obj);
								
							}
							else if(instruction[i]=='F')
							{
							  result=move_forward(obj);
							  if(result==0)
							  {
								  G[obj.posx][obj.posy][obj.dir]=1;
								  break;
							  }
							  
						     }	
					  
						}
						
						if(result==0)
						{
							out_orientation=set_orientation(obj.dir);
							
							System.out.println(obj.posx+" "+obj.posy+" "+out_orientation+" "+"LOST");
							
							 
						}
						else
						{
							out_orientation=set_orientation(obj.dir);
							System.out.println(obj.posx+" "+obj.posy+" "+out_orientation);
						}
						
					}
						
				}
				
			}
