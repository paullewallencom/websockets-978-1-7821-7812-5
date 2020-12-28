<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page With SSE EventSource</title>
        <script type="text/JavaScript">
            function startSSEConnection(){
                var source = new EventSource('SimpleDateServlet');
                source.addEventListener("server-time", function(event){                    
                document.getElementById("server-time").innerHTML=event.data;
                },false);                               
            }       
            
            </script>
            
 
 </script>
    </head>
    <body onload="startSSEConnection();">
        <div id="server-time">[Server-Time]</div>
    </body>        
</html>
