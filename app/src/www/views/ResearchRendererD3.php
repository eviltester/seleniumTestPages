<?php
class ResearchRendererD3 {

    function echo_scripts(){
        echo <<< EOD
    <script type="text/javascript" src="http://d3js.org/d3.v3.min.js"></script>
EOD;
    }

    function echo_style(){
        echo <<< EOT
<style type="text/css">
 .node circle {
   fill: #fff;
   stroke: steelblue;
   stroke-width: 3px;
 }

 .node text { font: 12px sans-serif; }

 .link {
   fill: none;
   stroke: #ccc;
   stroke-width: 2px;
 }

    </style>
EOT;
    }

    private function echo_research_tree($anItem)
    {

        echo("{");
        echo('"name":"'. $anItem->title . '",');

        echo('"children": [');
        foreach ($anItem->items as $itemInArray) {
            $theIncVal = $this->echo_research_tree($itemInArray);
        }
        echo("]},");
    }

    private function echoTreeAsJsonForD3($research){

        echo('{"name":"Research",
               "children": [');

            // echo notes
            echo('{"name":"notes",');
            echo('"children": [');
            foreach($research->notes as $aNote){
                echo('{"name":"'.$aNote->text.'"},');
            }
            echo(']},');

            // echo tree
            echo('{"name":"tree",');
            echo('"children": [');
            $this->echo_research_tree($research->itemTree);
            echo(']},');

            // echo links
            echo('{"name":"links",');
            echo('"children": [');
            foreach($research->resources as $aResource){
                echo('{"name":"'.$aResource->title.'"},');
            }
            echo(']},');

        echo(']}');
    }

    function echoResearch($research){
        echo <<< EOD
<script type="text/javascript">
debugger;
//http://bl.ocks.org/d3noob/8323795
var margin = {top: 20, right: 120, bottom: 20, left: 120},
 width = 960 - margin.right - margin.left,
 height = 500 - margin.top - margin.bottom;

var i = 0;

var treeData = [
EOD;
        $this->echoTreeAsJsonForD3($research);
        echo <<< EOD
];

var tree = d3.layout.tree().size([height, width]);

var diagonal = d3.svg.diagonal()
    .projection(function(d) { return [d.y, d.x]; });

var svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
  .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");


var root = treeData[0];

update(root);

function update(source) {

  // Compute the new tree layout.
  var nodes = tree.nodes(root).reverse(),
	  links = tree.links(nodes);

  // Normalize for fixed-depth.
  nodes.forEach(function(d) { d.y = d.depth * 60; d.x = d.x + (d.depth * 10); });

  // Declare the nodes…
  var node = svg.selectAll("g.node")
	  .data(nodes, function(d) { return d.id || (d.id = ++i); });

  // Enter the nodes.
  var nodeEnter = node.enter().append("g")
	  .attr("class", "node")
	  .attr("transform", function(d) {
		  return "translate(" + d.y + "," + d.x + ")"; });

  nodeEnter.append("circle")
	  .attr("r", 10)
	  .style("fill", "#fff");

  nodeEnter.append("text")
	  .attr("x", function(d) {
		  return d.children || d._children ? -13 : 13; })
	  .attr("dy", ".35em")
	  .attr("text-anchor", function(d) {
		  return d.children || d._children ? "end" : "start"; })
	  .text(function(d) { return d.name; })
	  .style("fill-opacity", 1);

  // Declare the links…
  var link = svg.selectAll("path.link")
	  .data(links, function(d) { return d.target.id; });

  // Enter the links.
  link.enter().insert("path", "g")
	  .attr("class", "link")
	  .attr("d", diagonal);

}
</script>
EOD;

    }
}
?>