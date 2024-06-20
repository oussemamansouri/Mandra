(function() {
  "use strict";
  setTimeout(function(){
  /**
   * Easy selector helper function
   */
  const select = (el, all = false) => {
    el = el.trim()
    if (all) {
      return [...document.querySelectorAll(el)]
    } else {
      return document.querySelector(el)
    }
  }

  /**
   * Easy event listener function
   */
  const on = (type, el, listener, all = false) => {
    if (all) {
      select(el, all).forEach(e => e.addEventListener(type, listener))
    } else {
      select(el, all).addEventListener(type, listener)
    }
  }

  /**
   * Easy on scroll event listener
   */
  const onscroll = (el, listener) => {
    el.addEventListener('scroll', listener)
  }

  /**
   * Sidebar toggle
   */
  if (select('.toggle-sidebar-btn')) {
    on('click', '.toggle-sidebar-btn', function(e) {
    //  select('body').classList.toggle('toggle-sidebar')
    })
  }

  /**
   * Search bar toggle
   */
  if (select('.search-bar-toggle')) {
    on('click', '.search-bar-toggle', function(e) {
      select('.search-bar').classList.toggle('search-bar-show')
    })
  }

  /**
   * Navbar links active state on scroll
   */
  let navbarlinks = select('#navbar .scrollto', true)
  const navbarlinksActive = () => {
    let position = window.scrollY + 200
    navbarlinks.forEach(navbarlink => {
      if (!navbarlink.hash) return
      let section = select(navbarlink.hash)
      if (!section) return
      if (position >= section.offsetTop && position <= (section.offsetTop + section.offsetHeight)) {
        navbarlink.classList.add('active')
      } else {
        navbarlink.classList.remove('active')
      }
    })
  }
  window.addEventListener('load', navbarlinksActive)
  onscroll(document, navbarlinksActive)

  /**
   * Toggle .header-scrolled class to #header when page is scrolled
   */
  let selectHeader = select('#header')
  if (selectHeader) {
    const headerScrolled = () => {
      if (window.scrollY > 100) {
        selectHeader.classList.add('header-scrolled')
      } else {
        selectHeader.classList.remove('header-scrolled')
      }
    }
    window.addEventListener('load', headerScrolled)
    onscroll(document, headerScrolled)
  }

  /**
   * Back to top button
   */
  let backtotop = select('.back-to-top')
  if (backtotop) {
    const toggleBacktotop = () => {
      if (window.scrollY > 100) {
        backtotop.classList.add('active')
      } else {
        backtotop.classList.remove('active')
      }
    }
    window.addEventListener('load', toggleBacktotop)
    onscroll(document, toggleBacktotop)
  }

  /**
   * Initiate tooltips
   */
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
  var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
  })

  /**
   * Initiate quill editors
   */
  if (select('.quill-editor-default')) {
    new Quill('.quill-editor-default', {
      theme: 'snow'
    });
  }

  if (select('.quill-editor-bubble')) {
    new Quill('.quill-editor-bubble', {
      theme: 'bubble'
    });
  }

  if (select('.quill-editor-full')) {
    new Quill(".quill-editor-full", {
      modules: {
        toolbar: [
          [{
            font: []
          }, {
            size: []
          }],
          ["bold", "italic", "underline", "strike"],
          [{
              color: []
            },
            {
              background: []
            }
          ],
          [{
              script: "super"
            },
            {
              script: "sub"
            }
          ],
          [{
              list: "ordered"
            },
            {
              list: "bullet"
            },
            {
              indent: "-1"
            },
            {
              indent: "+1"
            }
          ],
          ["direction", {
            align: []
          }],
          ["link", "image", "video"],
          ["clean"]
        ]
      },
      theme: "snow"
    });
  }

  /**
   * Initiate TinyMCE Editor
   */

  var useDarkMode = window.matchMedia('(prefers-color-scheme: dark)').matches;

  tinymce.init({
    selector: 'textarea.tinymce-editor',
    plugins: 'print preview paste importcss searchreplace autolink autosave save directionality code visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists wordcount imagetools textpattern noneditable help charmap quickbars emoticons',
    imagetools_cors_hosts: ['picsum.photos'],
    menubar: 'file edit view insert format tools table help',
    toolbar: 'undo redo | bold italic underline strikethrough | fontselect fontsizeselect formatselect | alignleft aligncenter alignright alignjustify | outdent indent |  numlist bullist | forecolor backcolor removeformat | pagebreak | charmap emoticons | fullscreen  preview save print | insertfile image media template link anchor codesample | ltr rtl',
    toolbar_sticky: true,
    autosave_ask_before_unload: true,
    autosave_interval: '30s',
    autosave_prefix: '{path}{query}-{id}-',
    autosave_restore_when_empty: false,
    autosave_retention: '2m',
    image_advtab: true,
    link_list: [{
        title: 'My page 1',
        value: 'https://www.tiny.cloud'
      },
      {
        title: 'My page 2',
        value: 'http://www.moxiecode.com'
      }
    ],
    image_list: [{
        title: 'My page 1',
        value: 'https://www.tiny.cloud'
      },
      {
        title: 'My page 2',
        value: 'http://www.moxiecode.com'
      }
    ],
    image_class_list: [{
        title: 'None',
        value: ''
      },
      {
        title: 'Some class',
        value: 'class-name'
      }
    ],
    importcss_append: true,
    file_picker_callback: function(callback, value, meta) {
      /* Provide file and text for the link dialog */
      if (meta.filetype === 'file') {
        callback('https://www.google.com/logos/google.jpg', {
          text: 'My text'
        });
      }

      /* Provide image and alt text for the image dialog */
      if (meta.filetype === 'image') {
        callback('https://www.google.com/logos/google.jpg', {
          alt: 'My alt text'
        });
      }

      /* Provide alternative source and posted for the media dialog */
      if (meta.filetype === 'media') {
        callback('movie.mp4', {
          source2: 'alt.ogg',
          poster: 'https://www.google.com/logos/google.jpg'
        });
      }
    },
    templates: [{
        title: 'New Table',
        description: 'creates a new table',
        content: '<div class="mceTmpl"><table width="98%%"  border="0" cellspacing="0" cellpadding="0"><tr><th scope="col"> </th><th scope="col"> </th></tr><tr><td> </td><td> </td></tr></table></div>'
      },
      {
        title: 'Starting my story',
        description: 'A cure for writers block',
        content: 'Once upon a time...'
      },
      {
        title: 'New list with dates',
        description: 'New List with dates',
        content: '<div class="mceTmpl"><span class="cdate">cdate</span><br /><span class="mdate">mdate</span><h2>My List</h2><ul><li></li><li></li></ul></div>'
      }
    ],
    template_cdate_format: '[Date Created (CDATE): %m/%d/%Y : %H:%M:%S]',
    template_mdate_format: '[Date Modified (MDATE): %m/%d/%Y : %H:%M:%S]',
    height: 600,
    image_caption: true,
    quickbars_selection_toolbar: 'bold italic | quicklink h2 h3 blockquote quickimage quicktable',
    noneditable_noneditable_class: 'mceNonEditable',
    toolbar_mode: 'sliding',
    contextmenu: 'link image imagetools table',
    skin: useDarkMode ? 'oxide-dark' : 'oxide',
    content_css: useDarkMode ? 'dark' : 'default',
    content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:14px }'
  });

  /**
   * Initiate Bootstrap validation check
   */
  var needsValidation = document.querySelectorAll('.needs-validation')

  Array.prototype.slice.call(needsValidation)
    .forEach(function(form) {
      form.addEventListener('submit', function(event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })

  /**
   * Initiate Datatables
   */
  const datatables = select('.datatable', true)
  datatables.forEach(datatable => {
    new simpleDatatables.DataTable(datatable);
  })

  /**
   * Autoresize echart charts
   */
  const mainContainer = select('#main');
  if (mainContainer) {
    setTimeout(() => {
      new ResizeObserver(function() {
        select('.echart', true).forEach(getEchart => {
          echarts.getInstanceByDom(getEchart).resize();
        })
      }).observe(mainContainer);
    }, 200);
  }
  /*Charts*/
  var budgetChart = echarts.init(document.querySelector("#budgetChart")).setOption({
    legend: {
      data: ['Allocated Budget', 'Actual Spending']
    },
    radar: {
      // shape: 'circle',
      indicator: [{
          name: 'Sales',
          max: 6500
        },
        {
          name: 'Administration',
          max: 16000
        },
        {
          name: 'Information Technology',
          max: 30000
        },
        {
          name: 'Customer Support',
          max: 38000
        },
        {
          name: 'Development',
          max: 52000
        },
        {
          name: 'Marketing',
          max: 25000
        }
      ]
    },
    series: [{
      name: 'Budget vs spending',
      type: 'radar',
      data: [{
          value: [4200, 3000, 20000, 35000, 50000, 18000],
          name: 'Allocated Budget'
        },
        {
          value: [5000, 14000, 28000, 26000, 42000, 21000],
          name: 'Actual Spending'
        }
      ]
    }]
  });
  var trafficChart = echarts.init(document.querySelector("#trafficChart")).setOption({
    tooltip: {
      trigger: 'item'
    },
    legend: {
      top: '5%',
      left: 'center'
    },
    series: [{
      name: 'Access From',
      type: 'pie',
      radius: ['40%', '70%'],
      avoidLabelOverlap: false,
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: '18',
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: [{
          value: 1048,
          name: 'Search Engine'
        },
        {
          value: 735,
          name: 'Direct'
        },
        {
          value: 580,
          name: 'Email'
        },
        {
          value: 484,
          name: 'Union Ads'
        },
        {
          value: 300,
          name: 'Video Ads'
        }
      ]
    }]
  });

//Echart
var EareaChart = echarts.init(document.querySelector("#EareaChart")).setOption({
  xAxis: {
    type: 'category',
    boundaryGap: false,
    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  },
  yAxis: {
    type: 'value'
  },

  series: [{
    data: [820, 932, 901, 934, 1290, 1330, 1320],
    type: 'line',
    smooth: true,
    areaStyle: {}
  }]
});

var barChart = echarts.init(document.querySelector("#barChart")).setOption({
  xAxis: {
    type: 'category',
    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  },
  yAxis: {
    type: 'value'
  },
  series: [{
    data: [120, 200, 150, 80, 70, 110, 130],
    type: 'bar'
  }]
});

var verticalBarChart = echarts.init(document.querySelector("#verticalBarChart")).setOption({
  title: {
    text: 'World Population'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  legend: {},
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: {
    type: 'value',
    boundaryGap: [0, 0.01]
  },
  yAxis: {
    type: 'category',
    data: ['Brazil', 'Indonesia', 'USA', 'India', 'China', 'World']
  },
  series: [{
      name: '2011',
      type: 'bar',
      data: [18203, 23489, 29034, 104970, 131744, 630230]
    },
    {
      name: '2012',
      type: 'bar',
      data: [19325, 23438, 31000, 121594, 134141, 681807]
    }
  ]
});




  }, 1);
  new ApexCharts(document.querySelector("#reportsChart"), {
    series: [{
      name: 'Sales',
      data: [31, 40, 28, 51, 42, 82, 56],
    }, {
      name: 'Revenue',
      data: [11, 32, 45, 32, 34, 52, 41]
    }, {
      name: 'Customers',
      data: [15, 11, 32, 18, 9, 24, 11]
    }],
    chart: {
      height: 350,
      type: 'area',
      toolbar: {
        show: false
      },
    },
    markers: {
      size: 4
    },
    colors: ['#4154f1', '#2eca6a', '#ff771d'],
    fill: {
      type: "gradient",
      gradient: {
        shadeIntensity: 1,
        opacityFrom: 0.3,
        opacityTo: 0.4,
        stops: [0, 90, 100]
      }
    },
    dataLabels: {
      enabled: false
    },
    stroke: {
      curve: 'smooth',
      width: 2
    },
    xaxis: {
      type: 'datetime',
      categories: ["2018-09-19T00:00:00.000Z", "2018-09-19T01:30:00.000Z", "2018-09-19T02:30:00.000Z", "2018-09-19T03:30:00.000Z", "2018-09-19T04:30:00.000Z", "2018-09-19T05:30:00.000Z", "2018-09-19T06:30:00.000Z"]
    },
    tooltip: {
      x: {
        format: 'dd/MM/yy HH:mm'
      },
    }
  }).render();
  //ApexCharts
  var lineChart = new ApexCharts(document.querySelector("#AlineChart"), {
    series: [{
      name: "Desktops",
      data: [10, 41, 35, 51, 49, 62, 69, 91, 148]
    }],
    chart: {
      height: 350,
      type: 'line',
      zoom: {
        enabled: false
      }
    },
    dataLabels: {
      enabled: false
    },
    stroke: {
      curve: 'straight'
    },
    grid: {
      row: {
        colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
        opacity: 0.5
      },
    },
    xaxis: {
      categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'],
    }
  }).render();

  const series = {
    "monthDataSeries1": {
      "prices": [
        8107.85,
        8128.0,
        8122.9,
        8165.5,
        8340.7,
        8423.7,
        8423.5,
        8514.3,
        8481.85,
        8487.7,
        8506.9,
        8626.2,
        8668.95,
        8602.3,
        8607.55,
        8512.9,
        8496.25,
        8600.65,
        8881.1,
        9340.85
      ],
      "dates": [
        "13 Nov 2017",
        "14 Nov 2017",
        "15 Nov 2017",
        "16 Nov 2017",
        "17 Nov 2017",
        "20 Nov 2017",
        "21 Nov 2017",
        "22 Nov 2017",
        "23 Nov 2017",
        "24 Nov 2017",
        "27 Nov 2017",
        "28 Nov 2017",
        "29 Nov 2017",
        "30 Nov 2017",
        "01 Dec 2017",
        "04 Dec 2017",
        "05 Dec 2017",
        "06 Dec 2017",
        "07 Dec 2017",
        "08 Dec 2017"
      ]
    },
    "monthDataSeries2": {
      "prices": [
        8423.7,
        8423.5,
        8514.3,
        8481.85,
        8487.7,
        8506.9,
        8626.2,
        8668.95,
        8602.3,
        8607.55,
        8512.9,
        8496.25,
        8600.65,
        8881.1,
        9040.85,
        8340.7,
        8165.5,
        8122.9,
        8107.85,
        8128.0
      ],
      "dates": [
        "13 Nov 2017",
        "14 Nov 2017",
        "15 Nov 2017",
        "16 Nov 2017",
        "17 Nov 2017",
        "20 Nov 2017",
        "21 Nov 2017",
        "22 Nov 2017",
        "23 Nov 2017",
        "24 Nov 2017",
        "27 Nov 2017",
        "28 Nov 2017",
        "29 Nov 2017",
        "30 Nov 2017",
        "01 Dec 2017",
        "04 Dec 2017",
        "05 Dec 2017",
        "06 Dec 2017",
        "07 Dec 2017",
        "08 Dec 2017"
      ]
    },
    "monthDataSeries3": {
      "prices": [
        7114.25,
        7126.6,
        7116.95,
        7203.7,
        7233.75,
        7451.0,
        7381.15,
        7348.95,
        7347.75,
        7311.25,
        7266.4,
        7253.25,
        7215.45,
        7266.35,
        7315.25,
        7237.2,
        7191.4,
        7238.95,
        7222.6,
        7217.9,
        7359.3,
        7371.55,
        7371.15,
        7469.2,
        7429.25,
        7434.65,
        7451.1,
        7475.25,
        7566.25,
        7556.8,
        7525.55,
        7555.45,
        7560.9,
        7490.7,
        7527.6,
        7551.9,
        7514.85,
        7577.95,
        7592.3,
        7621.95,
        7707.95,
        7859.1,
        7815.7,
        7739.0,
        7778.7,
        7839.45,
        7756.45,
        7669.2,
        7580.45,
        7452.85,
        7617.25,
        7701.6,
        7606.8,
        7620.05,
        7513.85,
        7498.45,
        7575.45,
        7601.95,
        7589.1,
        7525.85,
        7569.5,
        7702.5,
        7812.7,
        7803.75,
        7816.3,
        7851.15,
        7912.2,
        7972.8,
        8145.0,
        8161.1,
        8121.05,
        8071.25,
        8088.2,
        8154.45,
        8148.3,
        8122.05,
        8132.65,
        8074.55,
        7952.8,
        7885.55,
        7733.9,
        7897.15,
        7973.15,
        7888.5,
        7842.8,
        7838.4,
        7909.85,
        7892.75,
        7897.75,
        7820.05,
        7904.4,
        7872.2,
        7847.5,
        7849.55,
        7789.6,
        7736.35,
        7819.4,
        7875.35,
        7871.8,
        8076.5,
        8114.8,
        8193.55,
        8217.1,
        8235.05,
        8215.3,
        8216.4,
        8301.55,
        8235.25,
        8229.75,
        8201.95,
        8164.95,
        8107.85,
        8128.0,
        8122.9,
        8165.5,
        8340.7,
        8423.7,
        8423.5,
        8514.3,
        8481.85,
        8487.7,
        8506.9,
        8626.2
      ],
      "dates": [
        "02 Jun 2017",
        "05 Jun 2017",
        "06 Jun 2017",
        "07 Jun 2017",
        "08 Jun 2017",
        "09 Jun 2017",
        "12 Jun 2017",
        "13 Jun 2017",
        "14 Jun 2017",
        "15 Jun 2017",
        "16 Jun 2017",
        "19 Jun 2017",
        "20 Jun 2017",
        "21 Jun 2017",
        "22 Jun 2017",
        "23 Jun 2017",
        "27 Jun 2017",
        "28 Jun 2017",
        "29 Jun 2017",
        "30 Jun 2017",
        "03 Jul 2017",
        "04 Jul 2017",
        "05 Jul 2017",
        "06 Jul 2017",
        "07 Jul 2017",
        "10 Jul 2017",
        "11 Jul 2017",
        "12 Jul 2017",
        "13 Jul 2017",
        "14 Jul 2017",
        "17 Jul 2017",
        "18 Jul 2017",
        "19 Jul 2017",
        "20 Jul 2017",
        "21 Jul 2017",
        "24 Jul 2017",
        "25 Jul 2017",
        "26 Jul 2017",
        "27 Jul 2017",
        "28 Jul 2017",
        "31 Jul 2017",
        "01 Aug 2017",
        "02 Aug 2017",
        "03 Aug 2017",
        "04 Aug 2017",
        "07 Aug 2017",
        "08 Aug 2017",
        "09 Aug 2017",
        "10 Aug 2017",
        "11 Aug 2017",
        "14 Aug 2017",
        "16 Aug 2017",
        "17 Aug 2017",
        "18 Aug 2017",
        "21 Aug 2017",
        "22 Aug 2017",
        "23 Aug 2017",
        "24 Aug 2017",
        "28 Aug 2017",
        "29 Aug 2017",
        "30 Aug 2017",
        "31 Aug 2017",
        "01 Sep 2017",
        "04 Sep 2017",
        "05 Sep 2017",
        "06 Sep 2017",
        "07 Sep 2017",
        "08 Sep 2017",
        "11 Sep 2017",
        "12 Sep 2017",
        "13 Sep 2017",
        "14 Sep 2017",
        "15 Sep 2017",
        "18 Sep 2017",
        "19 Sep 2017",
        "20 Sep 2017",
        "21 Sep 2017",
        "22 Sep 2017",
        "25 Sep 2017",
        "26 Sep 2017",
        "27 Sep 2017",
        "28 Sep 2017",
        "29 Sep 2017",
        "03 Oct 2017",
        "04 Oct 2017",
        "05 Oct 2017",
        "06 Oct 2017",
        "09 Oct 2017",
        "10 Oct 2017",
        "11 Oct 2017",
        "12 Oct 2017",
        "13 Oct 2017",
        "16 Oct 2017",
        "17 Oct 2017",
        "18 Oct 2017",
        "19 Oct 2017",
        "23 Oct 2017",
        "24 Oct 2017",
        "25 Oct 2017",
        "26 Oct 2017",
        "27 Oct 2017",
        "30 Oct 2017",
        "31 Oct 2017",
        "01 Nov 2017",
        "02 Nov 2017",
        "03 Nov 2017",
        "06 Nov 2017",
        "07 Nov 2017",
        "08 Nov 2017",
        "09 Nov 2017",
        "10 Nov 2017",
        "13 Nov 2017",
        "14 Nov 2017",
        "15 Nov 2017",
        "16 Nov 2017",
        "17 Nov 2017",
        "20 Nov 2017",
        "21 Nov 2017",
        "22 Nov 2017",
        "23 Nov 2017",
        "24 Nov 2017",
        "27 Nov 2017",
        "28 Nov 2017"
      ]
    }
  }
  var AareaChart = new ApexCharts(document.querySelector("#AareaChart"), {
    series: [{
      name: "STOCK ABC",
      data: series.monthDataSeries1.prices
    }],
    chart: {
      type: 'area',
      height: 350,
      zoom: {
        enabled: false
      }
    },
    dataLabels: {
      enabled: false
    },
    stroke: {
      curve: 'straight'
    },
    subtitle: {
      text: 'Price Movements',
      align: 'left'
    },
    labels: series.monthDataSeries1.dates,
    xaxis: {
      type: 'datetime',
    },
    yaxis: {
      opposite: true
    },
    legend: {
      horizontalAlign: 'left'
    }
  }).render();
  //Chart.js
  new Chart(document.querySelector('#lineChart'), {
    type: 'line',
    data: {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [{
        label: 'Line Chart',
        data: [65, 59, 80, 81, 56, 55, 40],
        fill: false,
        borderColor: 'rgb(75, 192, 192)',
        tension: 0.1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
  new Chart(document.querySelector('#barChart'), {
    type: 'bar',
    data: {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [{
        label: 'Bar Chart',
        data: [65, 59, 80, 81, 56, 55, 40],
        backgroundColor: [
          'rgba(255, 99, 132, 0.2)',
          'rgba(255, 159, 64, 0.2)',
          'rgba(255, 205, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(201, 203, 207, 0.2)'
        ],
        borderColor: [
          'rgb(255, 99, 132)',
          'rgb(255, 159, 64)',
          'rgb(255, 205, 86)',
          'rgb(75, 192, 192)',
          'rgb(54, 162, 235)',
          'rgb(153, 102, 255)',
          'rgb(201, 203, 207)'
        ],
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
  new Chart(document.querySelector('#pieChart'), {
    type: 'pie',
    data: {
      labels: [
        'Red',
        'Blue',
        'Yellow'
      ],
      datasets: [{
        label: 'My First Dataset',
        data: [300, 50, 100],
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(54, 162, 235)',
          'rgb(255, 205, 86)'
        ],
        hoverOffset: 4
      }]
    }
  });
  new Chart(document.querySelector('#doughnutChart'), {
    type: 'doughnut',
    data: {
      labels: [
        'Red',
        'Blue',
        'Yellow'
      ],
      datasets: [{
        label: 'My First Dataset',
        data: [300, 50, 100],
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(54, 162, 235)',
          'rgb(255, 205, 86)'
        ],
        hoverOffset: 4
      }]
    }
  });
  new Chart(document.querySelector('#radarChart'), {
    type: 'radar',
    data: {
      labels: [
        'Eating',
        'Drinking',
        'Sleeping',
        'Designing',
        'Coding',
        'Cycling',
        'Running'
      ],
      datasets: [{
        label: 'First Dataset',
        data: [65, 59, 90, 81, 56, 55, 40],
        fill: true,
        backgroundColor: 'rgba(255, 99, 132, 0.2)',
        borderColor: 'rgb(255, 99, 132)',
        pointBackgroundColor: 'rgb(255, 99, 132)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgb(255, 99, 132)'
      }, {
        label: 'Second Dataset',
        data: [28, 48, 40, 19, 96, 27, 100],
        fill: true,
        backgroundColor: 'rgba(54, 162, 235, 0.2)',
        borderColor: 'rgb(54, 162, 235)',
        pointBackgroundColor: 'rgb(54, 162, 235)',
        pointBorderColor: '#fff',
        pointHoverBackgroundColor: '#fff',
        pointHoverBorderColor: 'rgb(54, 162, 235)'
      }]
    },
    options: {
      elements: {
        line: {
          borderWidth: 3
        }
      }
    }
  });
  new Chart(document.querySelector('#polarAreaChart'), {
    type: 'polarArea',
    data: {
      labels: [
        'Red',
        'Green',
        'Yellow',
        'Grey',
        'Blue'
      ],
      datasets: [{
        label: 'My First Dataset',
        data: [11, 16, 7, 3, 14],
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(75, 192, 192)',
          'rgb(255, 205, 86)',
          'rgb(201, 203, 207)',
          'rgb(54, 162, 235)'
        ]
      }]
    }
  });
  new Chart(document.querySelector('#stakedBarChart'), {
    type: 'bar',
    data: {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [{
          label: 'Dataset 1',
          data: [-75, -15, 18, 48, 74],
          backgroundColor: 'rgb(255, 99, 132)',
        },
        {
          label: 'Dataset 2',
          data: [-11, -1, 12, 62, 95],
          backgroundColor: 'rgb(75, 192, 192)',
        },
        {
          label: 'Dataset 3',
          data: [-44, -5, 22, 35, 62],
          backgroundColor: 'rgb(255, 205, 86)',
        },
      ]
    },
    options: {
      plugins: {
        title: {
          display: true,
          text: 'Chart.js Bar Chart - Stacked'
        },
      },
      responsive: true,
      scales: {
        x: {
          stacked: true,
        },
        y: {
          stacked: true
        }
      }
    }
  });
  new Chart(document.querySelector('#bubbleChart'), {
    type: 'bubble',
    data: {
      labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
      datasets: [{
          label: 'Dataset 1',
          data: [{
              x: 20,
              y: 30,
              r: 15
            },
            {
              x: 40,
              y: 10,
              r: 10
            },
            {
              x: 15,
              y: 37,
              r: 12
            },
            {
              x: 32,
              y: 42,
              r: 33
            }
          ],
          borderColor: 'rgb(255, 99, 132)',
          backgroundColor: 'rgba(255, 99, 132, 0.5)'
        },
        {
          label: 'Dataset 2',
          data: [{
              x: 40,
              y: 25,
              r: 22
            },
            {
              x: 24,
              y: 47,
              r: 11
            },
            {
              x: 65,
              y: 11,
              r: 14
            },
            {
              x: 11,
              y: 55,
              r: 8
            }
          ],
          borderColor: 'rgb(75, 192, 192)',
          backgroundColor: 'rgba(75, 192, 192, 0.5)'
        }
      ]
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: 'top',
        },
        title: {
          display: true,
          text: 'Chart.js Bubble Chart'
        }
      }
    }
  });


})();
