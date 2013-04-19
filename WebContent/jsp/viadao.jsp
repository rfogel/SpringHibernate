<!doctype html>
 
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Mo Viadao Vc!</title>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
 
  <script>
  $(document).ready(function() {
	  $('#dialog').hide(); 
  });
  
  setTimeout(function(){
	    
  		$(function() {
  			$('#msg').hide(); 	
	    $( "#dialog" ).dialog({
	      autoOpen: true,
	      height:500,
	      width:650,
	      show: {
	        effect: "bounce",
	        duration: 1000
	      },
	      hide: {
	        effect: "bounce",
	        duration: 1000
	      }
	    });
	 
	    $( "#opener" ).click(function() {
	      $( "#dialog" ).dialog( "open" );
	    });
	    
	  });
  }, 3000);
  </script>
</head>
<body>
<div id="msg"><h1>Renderizando informação...</h1></div> 
 
<div id="dialog">
  <img src="Imagens/content_Viadagem.jpg"/>
</div>
 
<!--<button id="opener">Open Dialog</button>-->
 
 
</body>
</html>