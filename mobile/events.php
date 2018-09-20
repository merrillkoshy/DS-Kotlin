<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
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
<body>
	<style type="text/css" media="screen">
	body{
		margin: 5px;
	    margin-left: 9%;
	    margin-right: 9%;
	    text-align: left;
	    margin-top: 9%;
	    background-image: url("/mobile/dsbg.png");
	    background-size: contain;
	}
	a:-webkit-any-link {
		color:black;
	    /*color: #ff3d2e;*/
	    cursor: pointer;
	    text-decoration: underline;
	}
	.listing{padding: 3%;}
	.box{ 
		position: relative;
	    overflow: hidden;
	    border-radius: 10px;
	    background-color: #ffffffc4;
	    box-shadow: 0 22px 26px rgba(0, 0, 0, 0.5);
	    transition: 0.5s;
	    margin-bottom: 10%;}
	.small{color:red;}
	.label,.dis-inblk{display: none;}
	
	</style>	
	
</body>
</html>


<?php
	
	// example of how to use basic selector to retrieve HTML contents
	include('simple_html_dom.php');
	$dates = array();
	$images = array();
	$events = array();
	$venues = array();
	$detail = array();

	// get DOM from URL or file
	$html = file_get_html('https://10times.com/dubai-ae');
	
	
	// find all div tags with id=gbar
	foreach($html->find('#content > tr:nth-child(1) > td.text-drkr') as $e)
	    $dates[] = $e;
	foreach($html->find('#content > tr:nth-child(1) > td:nth-child(2) > a > img') as $e)
	    $images[] = $e;
	foreach($html->find('h2') as $f)
		$events[] = $f;	
	foreach($html->find('#content > tr:nth-child(1) > td:nth-child(3) > span') as $e)
	    $venues[] = $e;
	foreach($html->find('#content > tr:nth-child(1) > td:nth-child(4)') as $e)
	    $detail[] = $e->innertext;	

	$length = count($events);	
	
	for($x = 0; $x < $length; $x++) {
	echo '<div class="box"><div class="listing">';	
		echo $dates[$x];
    	echo "<br>";
    	echo $events[$x];
    	echo "<br>";
    	echo $venues[$x];
    	echo "<br></div></div>";   	    		
		}

	?>

