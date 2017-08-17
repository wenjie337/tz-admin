[#ftl]
<!DOCTYPE html>
<html lang="zh_CN" class="app">
<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	[#--<title><sitemesh:write property="title"/></title>--]
	<title>FWHD Admin V1.0</title>
	[#include '../common/css.ftl' /]
	<link rel="stylesheet" href="js/fullcalendar/fullcalendar.css" type="text/css"  />
    <link rel="stylesheet" href="js/fullcalendar/theme.css" type="text/css" />
    <script>
	    function tab(a,b){
	  		$('#ifhtml').attr("src",a + '?m=' + b);
	  		$('html').removeClass('open');
	  		$('#nav').removeClass('nav-off-screen');
	    }
    </script>
</head>
<body style="overflow:hidden;">
	<section class="vbox" >
		[#include '../common/header.ftl' /]
		<section>
			<section class="hbox stretch">
				[#if selsys?exists && selsys == 0]
					[#include '../common/menu_sys.ftl' /]
				[#else]
					[#include '../common/menu.ftl' /]
				[/#if]
				<section id="content" style="-webkit-overflow-scrolling:touch; width:100%;overflow-x:auto;overflow-y:hidden">
				    <iframe id="ifhtml" scrolling="yes" frameborder="0" src="main.htm" width="100%" height="100%" style="min-height:500px"/></iframe>
			        <a href="#" class="hide nav-off-screen-block" data-toggle="class:nav-off-screen, open" data-target="#nav,html"></a>
			    </section>
			    <aside class="bg-light lter b-l aside-md hide" id="notes">
			      <div class="wrapper">Notification</div>
			    </aside>
			</section>
		</section>
	</section>
	[#include '../common/js.ftl' /]
</body>
</html>