<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Ajax Continues Polling</title>
        
        <script>

function startUpdating(url,target) {
  if (window.XMLHttpRequest) {
    req = new XMLHttpRequest();
    req.onreadystatechange = function() {updateDiv();};
    req.open("GET", url, true);
    req.send(null);
    // IE/Windows ActiveX version
  } else if (window.ActiveXObject) {
    req = new ActiveXObject("Microsoft.XMLDOM");
    if (req) {
      req.onreadystatechange = function() {updateDiv();};
      req.open("GET", url, true);
      req.send(null);
    }
  }
  setTimeout("ajax(page,'scriptoutput')", 5000);
}

function updateDiv() {
      results = req.responseText;
      document.getElementById("dateDiv").innerHTML = results;
  }

        </script>
    </head>
    <body onload="startUpdating()">
        <div id="dateDiv"></div>
    </body>
</html>