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
    .slideup, .slidedn{
      display:flex;      
    }
    body{
      margin-left: 6%;
    }
    body > div.slideup > img,body > div.slidedn > img{    
    height: 211px;
    width: 349px;
    }    
    .nav{
        margin-top: 8px;
    text-align: center;}
  </style>
<script src="https://www.w3schools.com/lib/w3.js"></script>
<?php
	
	$servername = "localhost";
	$username = "diplomatsadmin";
	$pwd = "5reaK!@#";
	$dbname = "timeline_events.db";
	$parent="https://diplomatssummit.com/";
	$db =mysqli_connect($servername, $username, $pwd, $dbname);
	

	$sql="SELECT * FROM upcoming";
  echo '<div id="top" style="margin-top: 10%;"></div>';	
  echo '<h1 style="text-align: center;">UPCOMING EVENTS</h1>';
	if ($result=mysqli_query($db,$sql))
  {   
  // Fetch one and one row
  	echo "<div class=\"slideup\">";
       
  while ($row=mysqli_fetch_row($result))
    { 
    echo "<img class=\"ueva\" src=".$parent.$row[1].">";      
    }       
    echo "</div>";
    echo '<div class="nav">';
    echo '<button onclick="myShowu.previous()">Previous</button>';
    echo '<button onclick="myShowu.next()">Next</button>';
    echo "</div>"; 
  // Free result set
  mysqli_free_result($result);
}


$past="SELECT * FROM past";
echo '<h1 style="text-align: center;margin-top:10%;">PAST EVENTS</h1>';
    if ($pr=mysqli_query($db,$past))
    {     
    // Fetch one and one row
      echo "<div class=\"slidedn\">";
        
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

?>
<!DOCTYPE html>
<html>

<body> 
<script>
myShowu=w3.slideshow(".ueva", 5000);
myShowp=w3.slideshow(".peva", 5000);
</script> 
</body>
</html>