var canvas, ctx, flag = false,
    prevX = 0,
    currX = 0,
    prevY = 0,
    currY = 0,
    dot_flag = false,
    x = "black",
    brushSize = 2;

var snapshots = [];
var undoStep = 1;
var firstRedo = true;

var navbar = document.getElementById("navbar");
var navbarTitle = document.getElementById("navbarTitle");
var body = document.getElementById("body");
var chatList = document.getElementById("chatList");
var dialog = document.getElementById("dialog");
dialogPolyfill.registerDialog(dialog);

var isDrawer = false;
var freeDraw = false;

var roomFull = false;

function init() {
    canvas = document.getElementById('canvas');
    canvasPlaceholder = document.getElementById('canvasPlaceholder');
    ctx = canvas.getContext("2d");
    canvas.width = canvasPlaceholder.clientWidth;
    canvas.height = canvasPlaceholder.clientHeight;
    w = canvas.width;
    h = canvas.height;

    canvas.addEventListener("mousemove", function (e) {
        findxy('move', e)
    }, false);
    canvas.addEventListener("mousedown", function (e) {
        findxy('down', e)
    }, false);
    canvas.addEventListener("mouseup", function (e) {
        findxy('up', e)
    }, false);
    canvas.addEventListener("mouseout", function (e) {
        findxy('out', e)
    }, false);
    //Touch
    canvas.addEventListener("touchmove", function (e) {
        findxy('move', e)
    }, false);
    canvas.addEventListener("touchstart", function (e) {
        findxy('down', e)
    }, false);
    canvas.addEventListener("touchend", function (e) {
        findxy('up', e)
    }, false);
}