import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class Text {
	
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
		  
		 
		  public Text(int d,int x,int y,char o)
		  {
		  	posx=x;
		  	posy=y;
		  	dir=d;
		  	orientation=o;	
		  }
		  
		  public Text(char o,int d)
		  {
		  	orientation=o;
		  	dir=d;
		  }  
		  
	  
		  //methods 
		  
		  // method for setting the direction after getting the orientation
		  public static Text set_direction(Text o)
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
		   public static Text turn_left(Text o)
			  {
				  o.dir = (o.dir+1)%4;
				  
				  return o;
			  }
		   
		   //turn right for instruction "R"
		   public static Text turn_right(Text o)
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
		   public static boolean is_Blocked(Text o)
			  {
				  if(G[o.posx][o.posy][o.dir]==1)
				  {
					  return true;
				  }
				  
	               else
	            	   return false;	
			  }
			  
		   
		   //method for tracking the movement of the ship
		    public static int move_forward(Text obj)
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
		  
	public static void main (String[] args) throws Exception  {
		
		int x1=0;
		int y1=0;
		
		char ori='\0';
		char out_orientation='\0';
		int result=-1;
		ArrayList<String>list=new ArrayList<String>();
		
	  //for reading the data from the text file.
		
		try(BufferedReader br = new BufferedReader(new FileReader("ship.txt"))) {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine(); 

	        while (line != null) {
	            sb.append(line);
	            sb.append(System.lineSeparator());
	            list.add(line);
	            line = br.readLine(); 
	        }
	        
	        
            int s= list.size();
            String a=list.get(0);
            
            
           char arr[]=a.replaceAll(" ","").toCharArray();
           
       
           for(int i=0;i<arr.length-1;i++)
            {
        	 x=Character.getNumericValue(arr[i]); // top right coordinates of the grid.
        	 y=Character.getNumericValue(arr[i+1]); // top right coordinates of the grid

            }
             grid=new int[x+1][y+1];
		     G=new int[x+1][y+1][4];
           
          int i=1;
          while(i<s)
          {
        	String str=list.get(i);
        	
        	char arr1[]=str.replaceAll(" ","").toCharArray();
        	i++;
        	
        	ori=arr1[arr1.length-1];
        	for(int j=0;j<arr1.length-2;j++)
        	{
        		x1=Character.getNumericValue(arr1[j]); //current position of the ship
        		y1=Character.getNumericValue(arr1[j+1]); //current position of the ship
        	}
        	
        	ship_instruction=list.get(i);
        	i++;
        	Text obj=new Text(ori,x1,y1,ori);
			obj=set_direction(obj); // for setting the direction
			len=ship_instruction.length();
	        instruction=ship_instruction.toCharArray();
	        for(int l=0;l<len;l++)
			{
				if(instruction[l]=='L')
				{   
					obj=turn_left(obj);
					
				}
				else if (instruction[l]=='R')
				{
					
					obj=turn_right(obj);
					
				}
				else if(instruction[l]=='F')
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
	
}
