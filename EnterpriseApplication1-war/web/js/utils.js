//
//
//
///* global myApp */
//
//myApp.directive('ngMouseWheelUp', function() {
//        alert("OHO");
//        return function(scope, element, attrs) {
//            element.bind("DOMMouseScroll mousewheel onmousewheel", function(event) {
//                        alert("OHO");
//                   
//                        // cross-browser wheel delta
//                        var event = window.event || event; // old IE support
//                        var delta = Math.max(-1, Math.min(1, (event.wheelDelta || -event.detail)));
//                
//                        if(delta > 0) {
//                            scope.$apply(function(){
//                                scope.$eval(attrs.ngMouseWheelUp);
//                            });
//                        
//                            // for IE
//                            event.returnValue = false;
//                            // for Chrome and Firefox
//                            if(event.preventDefault) {
//                                event.preventDefault();                        
//                            }
//
//                        }
//            });
//        };
//});
//
//myApp.directive('ngMouseWheelDown', function() {
//        alert("OHO");
//        return function(scope, element, attrs) {
//            element.bind("DOMMouseScroll mousewheel onmousewheel", function(event) {
//                        // cross-browser wheel delta
//                        var event = window.event || event; // old IE support
//                        var delta = Math.max(-1, Math.min(1, (event.wheelDelta || -event.detail)));
//                
//                        if(delta < 0) {
//                            scope.$apply(function(){
//                                scope.$eval(attrs.ngMouseWheelDown);
//                            });
//                        
//                            // for IE
//                            event.returnValue = false;
//                            // for Chrome and Firefox
//                            if(event.preventDefault)  {
//                                event.preventDefault();
//                            }
//
//                        }
//            });
//        };
//});