<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>基于Vue.js的贵州茶叶种植与文化传播平台管理框架</h2>

      </el-col>

    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">

          <div class="body">
            <h2>
            贵州茶产业又交出一份漂亮的成绩单：截至当年底，贵州省茶园面积700万亩，茶叶产量45.4万吨，茶叶产值606.2亿元，同比增6.18%。
            近年来，省委、省政府高度重视、高位推动茶产业发展，按照贵州原料、贵州制造、贵州创造、贵州品牌的发展路径，以“贵州绿茶”品牌为引领，持续深耕东北、华北、西北“三北”目标市场，实施基地黔茶系列品种、茶叶专用肥、绿色防控推广“三大行动”，推动产业链基地生态化、加工标准化、企业集约化、市场品牌化“四化发展”，加速“茶+”融合。
            如今，贵州作为中国茶叶原料基地和加工中心初步形成，进入中国茶产业第一方阵。湄潭、凤冈、石阡、都匀等10个茶叶主产地入选“中国茶业百强县”，湄潭连续三年位居“中国茶业百强县”第一名。
            </h2>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <h2>
            贵州是国内唯一兼具高海拔、低纬度、寡日照、多云雾适宜种茶制茶条件的茶区，是国内业界公认的高品质绿茶重要产地。“翡翠绿、嫩栗香、浓爽味”的独特品质为贵州生产高品质绿茶奠定了坚实基础。
            绿色，是贵州茶叶的底色，更是贵州茶园的基本色。从源头守牢“干净茶”底线，贵州茶质量安全水平不断提高。贵州推行“生态为根、农艺为本、生防为先”的理念，推广以草治草、以虫治虫、免疫诱控等绿色防控集成技术，绿色防控技术覆盖率62.38%。瓮安黄红缨公司基地成为全国首批100个农作物病虫害防控示范基地。率先在茶园全面禁用水溶性农药和除草剂，禁限农药种类在全国62种基础上增加到128种，同时提出了28种出口茶园慎用农药。加工过程以茶青及制品不落地为前提，严格质量安全管理。开展茶叶质量安全专项监督检查，对违规使用草甘膦及违禁农药等情况零容忍，每年抽检茶样2500余个。
          </h2>
        </el-card>
      </el-col>

    </el-row>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.9"
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    }
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

