<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="profile" href="https://gmpg.org/xfn/11">
    <link rel="shortcut icon" href="/DS_og.png" />  
    <meta name="description" content="Explore Investment Opportunities across the globe">
  <meta property="og:title" content="Diplomats Summit" />
  <meta property="og:url" content="https://diplomatssummit.com/" />
  <meta property="og:type" content="website" />
  <meta property="og:description" content="Investment Opportunities">
  <meta property="og:image" content="/DS_og.png">
  <meta property="og:image:width" content="300" />
  <meta property="og:image:height" content="300" /> 
      
  <title>Diplomats Summit | Events </title>
  
</head> 
<style>
  #\23 divider{
    width: 50%;
    background-color: beige;
    padding:1%;
  }

	#\23 head{
		width: 180px;
    font-family: sans-serif;
    margin-left: 4%;
	}
    .slideup, .slidedn{
      display:flex;      
    }
    body{
      margin-left: 4%;
    }
    body > div.slideup > img,body > div.slidedn > img{    
    height: 211px;
    width: 180px;
    margin-left: 4%;
    }   
    #\23 dp{
      height: 211px;
      width: 180px;
    } 
    .nav{
        margin-top: 8px;
    text-align: right;}
  </style>
<script src="https://www.w3schools.com/lib/w3.js"></script>
<?php
	
	$servername = "localhost";
	$username = "dsadmin";
	$pwd = "5reaK!@#";
	$dbname = "gallery";	
	$db =mysqli_connect($servername, $username, $pwd, $dbname);
	
  // echo '<div id="#divider">';
	$sql="SELECT * FROM Arab_women";
  echo '<div id="top" style="margin-top: 10%;"></div>';	
  echo '<div id="#head">DS Director Sandy Nathan at the Arab Women Leadership and Business Summit</div>';
	if ($result=mysqli_query($db,$sql))
  {   
  // Fetch one and one row
  	echo "<div class=\"slideup\">";
       echo '<img id="#dp" src="https://diplomatssummit.com/wp-content/uploads/2018/04/0dac9943-147e-4dd8-94db-40aee5e5ea82-225x300.jpg" >';
  while ($row=mysqli_fetch_row($result))
    { 
    echo "<img class=\"ueva\" src=".$row[1].">";      
    }       
    echo "</div>";
    echo '<div class="nav">';
    echo '<button onclick="myShowu.previous()">Previous</button>';
    echo '<button onclick="myShowu.next()">Next</button>';
    echo "</div>"; 
  // Free result set
  mysqli_free_result($result);
}


$past="SELECT * FROM inno_tech";
echo '<div id="#head" margin-top:10%;">Empowering Startups/Innovation and Technology Investments</div>';
    if ($pr=mysqli_query($db,$past))
    {     
    // Fetch one and one row
      echo "<div class=\"slidedn\">";
       echo '<img id="#dp" src="https://diplomatssummit.com/wp-content/uploads/2018/04/DSES-1-300x200.jpg" style="height:114px;margin-top:49px;">'; 
    while ($rw=mysqli_fetch_row($pr))
      {       
      echo "<img class=\"peva\" src=".$parent.$rw[1].">";               
      }          
      echo "</div>";
      echo '<div class="nav">';
      echo '<button onclick="myShowp.previous()">Previous</button>';
      echo '<button onclick="myShowp.next()">Next</button>';
      echo "</div>";       
    // Free result set
    mysqli_free_result($pr);
  }

  $fl="SELECT * FROM film_launch";
echo '<div id="#head" margin-top:10%;">Film Launch Event-Selfie by Zenofar Fathima</div>';
    if ($fil=mysqli_query($db,$fl))
    {     
    // Fetch one and one row
      echo "<div class=\"slidedn\">";
       echo '<img id="#dp" src="https://diplomatssummit.com/wp-content/uploads/2018/04/Selfie_movie_poster-278x300.jpg">'; 
    while ($rw3=mysqli_fetch_row($fil))
      {       
      echo "<img class=\"feva\" src=".$parent.$rw3[1].">";               
      }          
      echo "</div>";
      echo '<div class="nav">';
      echo '<button onclick="myShow3.previous()">Previous</button>';
      echo '<button onclick="myShow3.next()">Next</button>';
      echo "</div>";       
    // Free result set
    mysqli_free_result($fil);
  }

  $aim="SELECT * FROM aim18";
echo '<div id="#head" margin-top:10%;">Annual Investment Meeting 2018</div>';
    if ($a8=mysqli_query($db,$aim))
    {     
    // Fetch one and one row
      echo "<div class=\"slidedn\">";
       echo '<img id="#dp" src="https://diplomatssummit.com/wp-content/uploads/2018/04/WhatsApp-Image-2018-04-09-at-5.49.37-PM-1-300x225.jpeg">'; 
    while ($rw4=mysqli_fetch_row($a8))
      {       
      echo "<img class=\"aeva\" src=".$parent.$rw4[1].">";               
      }          
      echo "</div>";
      echo '<div class="nav">';
      echo '<button onclick="myShow4.previous()">Previous</button>';
      echo '<button onclick="myShow4.next()">Next</button>';
      echo "</div>";       
    // Free result set
    mysqli_free_result($fil);
  }
// echo "</div>"; 
?>
<!DOCTYPE html>
<html>

<body>
<div class="space" style="margin-top: 30%;"></div>
<script>
myShowu=w3.slideshow(".ueva", 5000);
myShowp=w3.slideshow(".peva", 5000);
myShow3=w3.slideshow(".feva", 5000);
myShow4=w3.slideshow(".aeva", 5000);
</script>
</body>
</html>