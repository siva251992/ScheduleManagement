/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 6;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 69.81878775255156, "KoPercent": 30.18121224744845};
    var dataset = [
        {
            "label" : "KO",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "OK",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.020516559050197874, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [0.02, 500, 1500, "HTTP Request-3"], "isController": false}, {"data": [0.023333333333333334, 500, 1500, "HTTP Request-16"], "isController": false}, {"data": [0.021666666666666667, 500, 1500, "HTTP Request-2"], "isController": false}, {"data": [0.025, 500, 1500, "HTTP Request-15"], "isController": false}, {"data": [0.02, 500, 1500, "HTTP Request-5"], "isController": false}, {"data": [0.03166666666666667, 500, 1500, "HTTP Request-14"], "isController": false}, {"data": [0.016666666666666666, 500, 1500, "HTTP Request-4"], "isController": false}, {"data": [0.015, 500, 1500, "HTTP Request-13"], "isController": false}, {"data": [0.02, 500, 1500, "HTTP Request-1"], "isController": false}, {"data": [0.016666666666666666, 500, 1500, "HTTP Request-12"], "isController": false}, {"data": [0.021666666666666667, 500, 1500, "HTTP Request-11"], "isController": false}, {"data": [0.018333333333333333, 500, 1500, "HTTP Request-10"], "isController": false}, {"data": [0.018333333333333333, 500, 1500, "HTTP Request-7"], "isController": false}, {"data": [0.0, 500, 1500, "HTTP Request"], "isController": false}, {"data": [0.021666666666666667, 500, 1500, "HTTP Request-6"], "isController": false}, {"data": [0.021666666666666667, 500, 1500, "HTTP Request-9"], "isController": false}, {"data": [0.016666666666666666, 500, 1500, "HTTP Request-8"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 4801, 1449, 30.18121224744845, 1775.192043324305, 542, 11477, 1963.0, 2014.8999999999996, 2125.9799999999996, 0.5638790651470003, 0.6155278505871989, 0.4549628032215336], "isController": false}, "titles": ["Label", "#Samples", "KO", "Error %", "Average", "Min", "Max", "90th pct", "95th pct", "99th pct", "Transactions\/s", "Received", "Sent"], "items": [{"data": ["HTTP Request-3", 300, 78, 26.0, 1773.7666666666678, 603, 2299, 1957.9, 2016.85, 2128.98, 0.03536580621660142, 0.040397374987769324, 0.028492959110054852], "isController": false}, {"data": ["HTTP Request-16", 300, 100, 33.333333333333336, 1769.2666666666667, 843, 2142, 1961.1000000000004, 2036.85, 2110.6800000000003, 0.035360270473066197, 0.035625012081425746, 0.028557562188697018], "isController": false}, {"data": ["HTTP Request-2", 300, 29, 9.666666666666666, 1785.6700000000012, 1211, 2375, 1969.4, 2027.6, 2151.7200000000003, 0.03536577703268635, 0.04131855567463344, 0.028492935597623278], "isController": false}, {"data": ["HTTP Request-15", 300, 100, 33.333333333333336, 1768.26, 886, 2326, 1960.9, 2011.8, 2124.5700000000006, 0.03536090399443843, 0.03591295769091411, 0.028558073831445888], "isController": false}, {"data": ["HTTP Request-5", 300, 100, 33.333333333333336, 1774.0766666666661, 542, 2238, 1958.0, 1994.6, 2095.92, 0.035364301223215815, 0.04086407170153332, 0.02852628204138307], "isController": false}, {"data": ["HTTP Request-14", 300, 100, 33.333333333333336, 1766.9300000000003, 1016, 2223, 1967.7, 2027.3999999999999, 2133.96, 0.03536007041840157, 0.036325105673570446, 0.028557400621111423], "isController": false}, {"data": ["HTTP Request-4", 300, 100, 33.333333333333336, 1765.4099999999994, 1116, 2254, 1983.3000000000002, 2016.6499999999999, 2090.88, 0.03536514750567257, 0.03986741167819649, 0.02851556771582878], "isController": false}, {"data": ["HTTP Request-13", 300, 100, 33.333333333333336, 1771.6266666666677, 1092, 2253, 1960.7, 2028.0, 2187.1100000000006, 0.035360628910001544, 0.03666501252591344, 0.028557851668526632], "isController": false}, {"data": ["HTTP Request-1", 300, 41, 13.666666666666666, 1790.2766666666666, 544, 5442, 1957.7, 2025.6999999999998, 2184.9300000000003, 0.0353515660213474, 0.042488393565331524, 0.028469058011566326], "isController": false}, {"data": ["HTTP Request-12", 300, 100, 33.333333333333336, 1787.866666666667, 1278, 2340, 1977.8000000000002, 2018.9, 2233.900000000001, 0.035361675087263775, 0.03703836180380376, 0.028558696579264783], "isController": false}, {"data": ["HTTP Request-11", 300, 100, 33.333333333333336, 1764.4966666666662, 776, 2163, 1958.9, 1996.6499999999999, 2124.95, 0.03536236701539855, 0.03755317615939941, 0.028559255392318943], "isController": false}, {"data": ["HTTP Request-10", 300, 100, 33.333333333333336, 1769.9966666666662, 1038, 2125, 1968.8000000000002, 2008.55, 2084.92, 0.035362104412978455, 0.037517443094712215, 0.028559043310091004], "isController": false}, {"data": ["HTTP Request-7", 300, 100, 33.333333333333336, 1783.9100000000014, 956, 2407, 1959.0, 2044.4499999999998, 2178.83, 0.035364301223215815, 0.03878469223214872, 0.02852628204138307], "isController": false}, {"data": ["HTTP Request", 1, 1, 100.0, 11477.0, 11477, 11477, 11477.0, 11477.0, 11477.0, 0.08713078330574191, 0.03948113618541431, 0.07113411605820336], "isController": false}, {"data": ["HTTP Request-6", 300, 100, 33.333333333333336, 1758.4833333333324, 911, 2241, 1962.8000000000002, 2007.6999999999998, 2135.0, 0.0353637134350991, 0.04096987503760341, 0.02852580790760923], "isController": false}, {"data": ["HTTP Request-9", 300, 100, 33.333333333333336, 1760.1000000000004, 942, 2125, 1957.9, 1998.0, 2095.6800000000003, 0.03536298810648408, 0.03803247929850676, 0.02852522282808189], "isController": false}, {"data": ["HTTP Request-8", 300, 100, 33.333333333333336, 1780.596666666666, 1234, 2239, 1967.7, 2044.4499999999998, 2131.9300000000003, 0.035363038128191956, 0.038334362152166214, 0.02852526317762359], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Percentile 1
            case 8:
            // Percentile 2
            case 9:
            // Percentile 3
            case 10:
            // Throughput
            case 11:
            // Kbytes/s
            case 12:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["502\/Bad Gateway", 1, 0.06901311249137336, 0.02082899395959175], "isController": false}, {"data": ["Value expected to match regexp \'true\', but it did not match: \'initial\'", 1448, 99.93098688750862, 30.160383253488856], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 4801, 1449, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 1448, "502\/Bad Gateway", 1, null, null, null, null, null, null], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": ["HTTP Request-3", 300, 78, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 78, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-16", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-2", 300, 29, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 29, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-15", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-5", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-14", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-4", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-13", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-1", 300, 41, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 41, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-12", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-11", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-10", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-7", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request", 1, 1, "502\/Bad Gateway", 1, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-6", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-9", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}, {"data": ["HTTP Request-8", 300, 100, "Value expected to match regexp \'true\', but it did not match: \'initial\'", 100, null, null, null, null, null, null, null, null], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
