package me.n1ar4.jar.analyzer.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import me.n1ar4.jar.analyzer.config.ConfigEngine;
import me.n1ar4.jar.analyzer.config.ConfigFile;
import me.n1ar4.jar.analyzer.decompile.DecompileEngine;
import me.n1ar4.jar.analyzer.dto.MethodResult;
import me.n1ar4.jar.analyzer.engine.CoreEngine;
import me.n1ar4.jar.analyzer.env.Const;
import me.n1ar4.jar.analyzer.gui.action.*;
import me.n1ar4.jar.analyzer.gui.adapter.AuthorAdapter;
import me.n1ar4.jar.analyzer.gui.adapter.CommonMouseAdapter;
import me.n1ar4.jar.analyzer.gui.adapter.TreeMouseAdapter;
import me.n1ar4.jar.analyzer.gui.adapter.TreeRightMenuAdapter;
import me.n1ar4.jar.analyzer.gui.render.AllMethodsRender;
import me.n1ar4.jar.analyzer.gui.render.MethodCallRender;
import me.n1ar4.jar.analyzer.gui.tree.FileTree;
import me.n1ar4.jar.analyzer.gui.util.*;
import me.n1ar4.jar.analyzer.utils.DirUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainForm {
    private static final Logger logger = LogManager.getLogger();
    private JPanel masterPanel;
    private JTabbedPane tabbedPanel;
    private JPanel codePanel;
    private JPanel corePanel;
    private JPanel startPanel;
    private JButton choseBtn;
    private JTextField fileText;
    private JButton startEngineButton;
    private JCheckBox resolveJarsInJarCheckBox;
    private JPanel chosePanel;
    private JRadioButton fernRadio;
    private JPanel decompilerPanel;
    private JProgressBar buildBar;
    private JPanel infoPanel;
    private JLabel totalClassLabel;
    private JLabel totalClassVal;
    private JLabel totalMethodLabel;
    private JLabel totalMethodVal;
    private JLabel totalJarLabel;
    private JLabel totalJarVal;
    private JLabel databaseSizeLabel;
    private JLabel databaseSizeVal;
    private JRadioButton methodDefinitionRadioButton;
    private JRadioButton methodCallRadioButton;
    private JButton startSearchButton;
    private JRadioButton stringContainsRadioButton;
    private JRadioButton binarySearchRadioButton;
    private JTextField searchClassText;
    private JPanel leftPanel;
    private JScrollPane treeScrollPanel;
    private FileTree fileTree;
    private JPanel logPanel;
    private JScrollPane logScroll;
    private JTextArea logArea;
    private JPanel curMethodPanel;
    private JScrollPane allMethodScroll;
    private JList<MethodResult> allMethodList;
    private JPanel historyPanel;
    private JPanel advancePanel;
    private JScrollPane hisScroll;
    private JList<MethodResult> callerList;
    private JList<MethodResult> calleeList;
    private JList<MethodResult> historyList;
    private JTextField loadDBText;
    private JLabel dbPathLabel;
    private JLabel engineVal;
    private JLabel engineLabel;
    private JCheckBox autoSaveCheckBox;
    private JTextField curClassText;
    private JTextField curMethodText;
    private JLabel curClassLabel;
    private JLabel curMethodLabel;
    private JTextField searchMethodText;
    private JTextField searchStrText;
    private JPanel searchResPanel;
    private JScrollPane searchScroll;
    private JList<MethodResult> searchList;
    private JTextField curJarText;
    private JLabel curJarLabel;
    private JTextField rtText;
    private JLabel jreRuntimeLabel;
    private JCheckBox autoFindRtJarCheckBox;
    private JCheckBox addRtJarWhenCheckBox;
    private JButton opcodeBtn;
    private JButton javaAsmBtn;
    private JButton JNDIButton;
    private JButton runtimeExecButton;
    private JButton processBuilderStartButton;
    private JButton spELGetValueButton;
    private JButton readObjectButton;
    private JButton scriptEngineEvalButton;
    private JButton BCELLoadClassButton;
    private JButton defineClassButton;
    private JButton OGNLGetValueButton;
    private JPanel javaVulSearchPanel;
    private JLabel javaVulLabel;
    private JTextField apiKeyText;
    private JTextField gptHostText;
    private JButton gptStartBtn;
    private JTextArea gptResultArea;
    private JPanel chatgptPanel;
    private JLabel apiKeyLabel;
    private JLabel apiKeyHost;
    private JPanel resultPanel;
    private JScrollPane gptResultScroll;
    private JLabel logoLabel;
    private JPanel authorPanel;
    private JLabel authorLabel;
    private JLabel authorTextLabel;
    private JLabel gptLabel;
    private JPanel curPanel;
    private JPanel methodImplPanel;
    private JScrollPane implScroll;
    private JList<MethodResult> methodImplList;
    private JCheckBox deleteTempCheckBox;
    private JPanel callerPanel;
    private JScrollPane callerScroll;
    private JPanel calleePanel;
    private JScrollPane calleeScroll;
    private JPanel callPanel;
    private JPanel searchOptionsPanel;
    private JPanel searchInnerPanel;
    private JLabel searchClassLabel;
    private JLabel searchMethodLabel;
    private JLabel searchStrLabel;
    private JScrollPane superImplScroll;
    private JList<MethodResult> superImplList;
    private JPanel pluginsPanel;
    private JPanel piPanel;
    private JPanel analysis;
    private JButton cfgBtn;
    private JLabel cfgLabel;
    private JLabel frameLabel;
    private JButton frameBtn;
    private JButton encoderBtn;
    private JLabel encoderLabel;
    private JButton repeaterBtn;
    private JLabel repeaterLabel;
    private JButton listenerBtn;
    private JLabel listenerLabel;
    private static MainForm instance;
    private static ConfigFile config;
    private static CoreEngine engine;
    private static JTextArea codeArea;
    private static MethodResult curMethod;
    private static DefaultListModel<MethodResult> historyListData;

    public JButton getEncoderBtn() {
        return encoderBtn;
    }

    public JButton getRepeaterBtn() {
        return repeaterBtn;
    }

    public JButton getListenerBtn() {
        return listenerBtn;
    }

    public static MainForm getInstance() {
        return instance;
    }

    public static void setCodeArea(JTextArea codeArea) {
        MainForm.codeArea = codeArea;
    }

    public static MethodResult getCurMethod() {
        return curMethod;
    }

    public static void setCurMethod(MethodResult curMethod) {
        MainForm.curMethod = curMethod;
    }

    public static JTextArea getCodeArea() {
        return codeArea;
    }

    public FileTree getFileTree() {
        return fileTree;
    }

    public JPanel getMasterPanel() {
        return masterPanel;
    }

    public JButton getChoseBtn() {
        return choseBtn;
    }

    public JButton getStartBuildDatabaseButton() {
        return startEngineButton;
    }

    public JTextField getFileText() {
        return fileText;
    }

    public JProgressBar getBuildBar() {
        return buildBar;
    }

    public JCheckBox getResolveJarsInJarCheckBox() {
        return resolveJarsInJarCheckBox;
    }

    public JLabel getTotalClassVal() {
        return totalClassVal;
    }

    public JLabel getTotalMethodVal() {
        return totalMethodVal;
    }

    public JLabel getTotalJarVal() {
        return totalJarVal;
    }

    public JLabel getDatabaseSizeVal() {
        return databaseSizeVal;
    }

    public static CoreEngine getEngine() {
        return engine;
    }

    public static void setEngine(CoreEngine engine) {
        MainForm.engine = engine;
    }

    public static ConfigFile getConfig() {
        return config;
    }

    public static void setConfig(ConfigFile config) {
        MainForm.config = config;
    }

    public JLabel getEngineVal() {
        return engineVal;
    }

    public JCheckBox getAutoSaveCheckBox() {
        return autoSaveCheckBox;
    }

    public JTextField getLoadDBText() {
        return loadDBText;
    }

    public JList<MethodResult> getAllMethodList() {
        return allMethodList;
    }

    public JTextField getCurClassText() {
        return curClassText;
    }

    public JTextField getCurJarText() {
        return curJarText;
    }

    public JTextField getCurMethodText() {
        return curMethodText;
    }

    public JTextField getSearchClassText() {
        return searchClassText;
    }

    public JButton getStartSearchButton() {
        return startSearchButton;
    }

    public JTextField getSearchMethodText() {
        return searchMethodText;
    }

    public JTextField getSearchStrText() {
        return searchStrText;
    }

    public JRadioButton getMethodDefinitionRadioButton() {
        return methodDefinitionRadioButton;
    }

    public JRadioButton getMethodCallRadioButton() {
        return methodCallRadioButton;
    }

    public JRadioButton getStringContainsRadioButton() {
        return stringContainsRadioButton;
    }

    public JRadioButton getBinarySearchRadioButton() {
        return binarySearchRadioButton;
    }

    public JList<MethodResult> getCallerList() {
        return callerList;
    }

    public JList<MethodResult> getMethodImplList() {
        return methodImplList;
    }

    public JList<MethodResult> getCalleeList() {
        return calleeList;
    }

    public JList<MethodResult> getSearchList() {
        return searchList;
    }

    public JTabbedPane getTabbedPanel() {
        return tabbedPanel;
    }

    public static DefaultListModel<MethodResult> getHistoryListData() {
        return historyListData;
    }

    public JList<MethodResult> getHistoryList() {
        return historyList;
    }

    public JTextField getRtText() {
        return rtText;
    }

    public JCheckBox getAutoFindRtJarCheckBox() {
        return autoFindRtJarCheckBox;
    }

    public JCheckBox getAddRtJarWhenCheckBox() {
        return addRtJarWhenCheckBox;
    }

    public JRadioButton getFernRadio() {
        return fernRadio;
    }

    public JButton getOpcodeBtn() {
        return opcodeBtn;
    }

    public JButton getJavaAsmBtn() {
        return javaAsmBtn;
    }

    public JButton getCfgBtn() {
        return cfgBtn;
    }

    public JButton getFrameBtn() {
        return frameBtn;
    }

    public JTextField getApiKeyText() {
        return apiKeyText;
    }

    public JTextField getGptHostText() {
        return gptHostText;
    }

    public JButton getGptStartBtn() {
        return gptStartBtn;
    }

    public JTextArea getGptResultArea() {
        return gptResultArea;
    }

    public JCheckBox getDeleteTempCheckBox() {
        return deleteTempCheckBox;
    }

    public JList<MethodResult> getSuperImplList() {
        return superImplList;
    }

    public MainForm() {
        logger.info("init main form");
        methodDefinitionRadioButton.setSelected(true);
        fernRadio.setSelected(true);
        fernRadio.setText(DecompileEngine.INFO);
        searchStrText.setEnabled(false);
        deleteTempCheckBox.setSelected(true);
        LogUtil.setT(logArea);
        SyntaxAreaHelper.buildJava(codePanel);
        engineVal.setText("CLOSED");
        engineVal.setForeground(Color.RED);
        autoSaveCheckBox.setSelected(true);
        if (ConfigEngine.exist()) {
            config = ConfigEngine.parseConfig();
            if (config != null) {
                String temp = config.getTempPath();
                String db = config.getDbPath();
                if (Files.exists(Paths.get(temp)) && Files.exists(Paths.get(db))) {
                    databaseSizeVal.setText(config.getDbSize());
                    totalClassVal.setText(config.getTotalClass());
                    totalJarVal.setText(config.getTotalJar());
                    totalMethodVal.setText(config.getTotalMethod());
                    fileText.setText(config.getJarPath());
                    loadDBText.setText(config.getDbPath());
                    String gptHost = config.getGptHost();
                    String gptKey = config.getGptKey();
                    gptHostText.setText(gptHost);
                    apiKeyText.setText(gptKey);

                    engine = new CoreEngine(config);
                    engineVal.setText("RUNNING");
                    engineVal.setForeground(Color.GREEN);
                    buildBar.setValue(100);
                } else {
                    try {
                        Files.delete(Paths.get(ConfigEngine.CONFIG_FILE_PATH));
                    } catch (Exception ignored) {
                    }
                    try {
                        DirUtil.removeDir(new File(Const.tempDir));
                    } catch (Exception ignored) {
                    }
                }
            }
        }
        fileTree.refresh();

        allMethodList.setCellRenderer(new AllMethodsRender());
        calleeList.setCellRenderer(new MethodCallRender());
        callerList.setCellRenderer(new MethodCallRender());
        searchList.setCellRenderer(new MethodCallRender());
        methodImplList.setCellRenderer(new MethodCallRender());
        superImplList.setCellRenderer(new MethodCallRender());

        historyList.setCellRenderer(new MethodCallRender());
        historyListData = new DefaultListModel<>();
        historyList.setModel(historyListData);

        logoLabel.setIcon(IconManager.showIcon);
        jreRuntimeLabel.setIcon(IconManager.javaIcon);
        dbPathLabel.setIcon(IconManager.dbIcon);
        startEngineButton.setIcon(IconManager.startIcon);
        curJarLabel.setIcon(IconManager.jarIcon);
        curClassLabel.setIcon(IconManager.curIcon);
        curMethodLabel.setIcon(IconManager.curIcon);
        authorLabel.setIcon(IconManager.auIcon);
        authorTextLabel.setIcon(IconManager.githubIcon);
        gptLabel.setIcon(IconManager.chatIcon);
        authorTextLabel.addMouseListener(new AuthorAdapter());

        logger.info("init main form success");
    }

    private static void init() {
        ChoseJarAction.run();
        BuildAction.run();
        JarsInJarAction.run();
        CommonSearchAction.run();
        SearchAction.run();
        RuntimeJarAction.run();
        ASMAction.run();
        GPTAction.run();
        PluginsAction.run();

        instance.fileTree.addMouseListener(new TreeMouseAdapter());
        instance.fileTree.addMouseListener(new TreeRightMenuAdapter());
        instance.allMethodList.addMouseListener(new CommonMouseAdapter());
        instance.callerList.addMouseListener(new CommonMouseAdapter());
        instance.calleeList.addMouseListener(new CommonMouseAdapter());
        instance.methodImplList.addMouseListener(new CommonMouseAdapter());
        instance.superImplList.addMouseListener(new CommonMouseAdapter());
        instance.searchList.addMouseListener(new CommonMouseAdapter());
        instance.historyList.addMouseListener(new CommonMouseAdapter());
    }

    public static void start() {
        UIHelper.setup();
        JFrame frame = new JFrame(Const.app);
        instance = new MainForm();

        init();

        frame.setJMenuBar(MenuUtil.createMenuBar());
        frame.setContentPane(instance.masterPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setIconImage(IconManager.showIcon.getImage());

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        masterPanel = new JPanel();
        masterPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        corePanel = new JPanel();
        corePanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        masterPanel.add(corePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        codePanel = new JPanel();
        codePanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        corePanel.add(codePanel, new GridConstraints(0, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(600, 750), new Dimension(600, 750), new Dimension(600, 750), 0, false));
        codePanel.setBorder(BorderFactory.createTitledBorder(null, "Java Decompile Code", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        tabbedPanel = new JTabbedPane();
        corePanel.add(tabbedPanel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, -1), new Dimension(500, 200), new Dimension(500, -1), 0, false));
        startPanel = new JPanel();
        startPanel.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPanel.addTab("start", startPanel);
        chosePanel = new JPanel();
        chosePanel.setLayout(new GridLayoutManager(6, 6, new Insets(0, 0, 0, 0), -1, -1));
        startPanel.add(chosePanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        chosePanel.setBorder(BorderFactory.createTitledBorder(null, "Starter", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        choseBtn = new JButton();
        choseBtn.setText("Chose Jar / Jars");
        chosePanel.add(choseBtn, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fileText = new JTextField();
        fileText.setEditable(false);
        fileText.setText("");
        chosePanel.add(fileText, new GridConstraints(0, 1, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        resolveJarsInJarCheckBox = new JCheckBox();
        resolveJarsInJarCheckBox.setText("Resolve Jars in Jar");
        chosePanel.add(resolveJarsInJarCheckBox, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buildBar = new JProgressBar();
        buildBar.setForeground(new Color(-9524737));
        buildBar.setStringPainted(true);
        chosePanel.add(buildBar, new GridConstraints(5, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        autoSaveCheckBox = new JCheckBox();
        autoSaveCheckBox.setText("Auto Save");
        chosePanel.add(autoSaveCheckBox, new GridConstraints(3, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dbPathLabel = new JLabel();
        dbPathLabel.setText("Database Path");
        chosePanel.add(dbPathLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        loadDBText = new JTextField();
        loadDBText.setEditable(false);
        chosePanel.add(loadDBText, new GridConstraints(1, 1, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        jreRuntimeLabel = new JLabel();
        jreRuntimeLabel.setText("JRE Runtime");
        chosePanel.add(jreRuntimeLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        rtText = new JTextField();
        rtText.setEditable(false);
        chosePanel.add(rtText, new GridConstraints(2, 1, 1, 5, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        autoFindRtJarCheckBox = new JCheckBox();
        autoFindRtJarCheckBox.setText("Auto Find rt.jar");
        chosePanel.add(autoFindRtJarCheckBox, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addRtJarWhenCheckBox = new JCheckBox();
        addRtJarWhenCheckBox.setText("Add rt.jar to Analyze");
        chosePanel.add(addRtJarWhenCheckBox, new GridConstraints(4, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        startEngineButton = new JButton();
        startEngineButton.setText("Start Engine");
        chosePanel.add(startEngineButton, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteTempCheckBox = new JCheckBox();
        deleteTempCheckBox.setText("Delete Temp");
        chosePanel.add(deleteTempCheckBox, new GridConstraints(3, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        decompilerPanel = new JPanel();
        decompilerPanel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        startPanel.add(decompilerPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        decompilerPanel.setBorder(BorderFactory.createTitledBorder(null, "Decompiler", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        fernRadio = new JRadioButton();
        fernRadio.setEnabled(true);
        fernRadio.setSelected(true);
        fernRadio.setText(" FernFlower");
        decompilerPanel.add(fernRadio, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        opcodeBtn = new JButton();
        opcodeBtn.setText("Show Method Opcode");
        decompilerPanel.add(opcodeBtn, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        javaAsmBtn = new JButton();
        javaAsmBtn.setText("Java ASM Code");
        decompilerPanel.add(javaAsmBtn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayoutManager(5, 3, new Insets(0, 0, 0, 0), -1, -1));
        startPanel.add(infoPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        infoPanel.setBorder(BorderFactory.createTitledBorder(null, "Information", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        totalClassLabel = new JLabel();
        totalClassLabel.setText("Total Class");
        infoPanel.add(totalClassLabel, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        totalClassVal = new JLabel();
        totalClassVal.setText("0");
        infoPanel.add(totalClassVal, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalMethodLabel = new JLabel();
        totalMethodLabel.setText("Total Method");
        infoPanel.add(totalMethodLabel, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        totalMethodVal = new JLabel();
        totalMethodVal.setText("0");
        infoPanel.add(totalMethodVal, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalJarLabel = new JLabel();
        totalJarLabel.setText("Total Jar");
        infoPanel.add(totalJarLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        totalJarVal = new JLabel();
        totalJarVal.setText("0");
        infoPanel.add(totalJarVal, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        databaseSizeLabel = new JLabel();
        databaseSizeLabel.setText("Database");
        infoPanel.add(databaseSizeLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        databaseSizeVal = new JLabel();
        databaseSizeVal.setText("0 MB");
        infoPanel.add(databaseSizeVal, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        engineLabel = new JLabel();
        engineLabel.setText("Engine State");
        infoPanel.add(engineLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        engineVal = new JLabel();
        engineVal.setText("CLOSED");
        infoPanel.add(engineVal, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        authorPanel = new JPanel();
        authorPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        infoPanel.add(authorPanel, new GridConstraints(0, 2, 5, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, -1), new Dimension(100, -1), new Dimension(100, -1), 0, false));
        authorLabel = new JLabel();
        authorLabel.setText("");
        authorPanel.add(authorLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        authorTextLabel = new JLabel();
        authorTextLabel.setText("4ra1n");
        authorPanel.add(authorTextLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        startPanel.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        analysis = new JPanel();
        analysis.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        startPanel.add(analysis, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        analysis.setBorder(BorderFactory.createTitledBorder(null, "Analysis", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        cfgBtn = new JButton();
        cfgBtn.setText("Show CFG");
        analysis.add(cfgBtn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        cfgLabel = new JLabel();
        cfgLabel.setText("Generate Control Flow Gragh Analysis");
        analysis.add(cfgLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        frameLabel = new JLabel();
        frameLabel.setText("Generate Java Stack Frame Analysis");
        analysis.add(frameLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        frameBtn = new JButton();
        frameBtn.setText("Show Frame");
        analysis.add(frameBtn, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, new Dimension(150, -1), 0, false));
        searchResPanel = new JPanel();
        searchResPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPanel.addTab("search", searchResPanel);
        searchScroll = new JScrollPane();
        searchResPanel.add(searchScroll, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        searchList = new JList();
        searchScroll.setViewportView(searchList);
        searchOptionsPanel = new JPanel();
        searchOptionsPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        searchResPanel.add(searchOptionsPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        searchOptionsPanel.setBorder(BorderFactory.createTitledBorder(null, "Search Options", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        methodDefinitionRadioButton = new JRadioButton();
        methodDefinitionRadioButton.setText("method definition");
        searchOptionsPanel.add(methodDefinitionRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        methodCallRadioButton = new JRadioButton();
        methodCallRadioButton.setText("method call");
        searchOptionsPanel.add(methodCallRadioButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stringContainsRadioButton = new JRadioButton();
        stringContainsRadioButton.setText("string contains");
        searchOptionsPanel.add(stringContainsRadioButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        binarySearchRadioButton = new JRadioButton();
        binarySearchRadioButton.setText("binary search");
        searchOptionsPanel.add(binarySearchRadioButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        searchInnerPanel = new JPanel();
        searchInnerPanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        searchOptionsPanel.add(searchInnerPanel, new GridConstraints(2, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        searchClassText = new JTextField();
        searchInnerPanel.add(searchClassText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchClassLabel = new JLabel();
        searchClassLabel.setText("Class");
        searchInnerPanel.add(searchClassLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        searchMethodLabel = new JLabel();
        searchMethodLabel.setText("Method");
        searchInnerPanel.add(searchMethodLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        searchMethodText = new JTextField();
        searchInnerPanel.add(searchMethodText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchStrLabel = new JLabel();
        searchStrLabel.setText("String");
        searchInnerPanel.add(searchStrLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        searchStrText = new JTextField();
        searchInnerPanel.add(searchStrText, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        startSearchButton = new JButton();
        startSearchButton.setText("Start Search");
        searchInnerPanel.add(startSearchButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logoLabel = new JLabel();
        logoLabel.setText("");
        searchInnerPanel.add(logoLabel, new GridConstraints(0, 2, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        callPanel = new JPanel();
        callPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPanel.addTab("call", callPanel);
        callerPanel = new JPanel();
        callerPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        callPanel.add(callerPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, 300), null, null, 0, false));
        callerPanel.setBorder(BorderFactory.createTitledBorder(null, "Caller", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        callerScroll = new JScrollPane();
        callerPanel.add(callerScroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        callerList = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        callerList.setModel(defaultListModel1);
        callerScroll.setViewportView(callerList);
        calleePanel = new JPanel();
        calleePanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        callPanel.add(calleePanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(500, 300), null, null, 0, false));
        calleePanel.setBorder(BorderFactory.createTitledBorder(null, "Callee", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        calleeScroll = new JScrollPane();
        calleePanel.add(calleeScroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        calleeList = new JList();
        calleeScroll.setViewportView(calleeList);
        methodImplPanel = new JPanel();
        methodImplPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPanel.addTab("impl", methodImplPanel);
        implScroll = new JScrollPane();
        methodImplPanel.add(implScroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        implScroll.setBorder(BorderFactory.createTitledBorder(null, "Method Impl", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        methodImplList = new JList();
        implScroll.setViewportView(methodImplList);
        superImplScroll = new JScrollPane();
        methodImplPanel.add(superImplScroll, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        superImplScroll.setBorder(BorderFactory.createTitledBorder(null, "Super Impl", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        superImplList = new JList();
        superImplScroll.setViewportView(superImplList);
        historyPanel = new JPanel();
        historyPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPanel.addTab("history", historyPanel);
        hisScroll = new JScrollPane();
        historyPanel.add(hisScroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        historyList = new JList();
        hisScroll.setViewportView(historyList);
        advancePanel = new JPanel();
        advancePanel.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPanel.addTab("advance", advancePanel);
        javaVulSearchPanel = new JPanel();
        javaVulSearchPanel.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        advancePanel.add(javaVulSearchPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        javaVulSearchPanel.setBorder(BorderFactory.createTitledBorder(null, "Java Vulnerability", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        JNDIButton = new JButton();
        JNDIButton.setText("JNDI");
        javaVulSearchPanel.add(JNDIButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        runtimeExecButton = new JButton();
        runtimeExecButton.setText("Runtime exec");
        javaVulSearchPanel.add(runtimeExecButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        processBuilderStartButton = new JButton();
        processBuilderStartButton.setText("ProcessBuilder start");
        javaVulSearchPanel.add(processBuilderStartButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        spELGetValueButton = new JButton();
        spELGetValueButton.setText("SpEL getValue");
        javaVulSearchPanel.add(spELGetValueButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        readObjectButton = new JButton();
        readObjectButton.setText("readObject");
        javaVulSearchPanel.add(readObjectButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scriptEngineEvalButton = new JButton();
        scriptEngineEvalButton.setText("ScriptEngine eval");
        javaVulSearchPanel.add(scriptEngineEvalButton, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        BCELLoadClassButton = new JButton();
        BCELLoadClassButton.setText("BCEL loadClass");
        javaVulSearchPanel.add(BCELLoadClassButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        defineClassButton = new JButton();
        defineClassButton.setText("defineClass");
        javaVulSearchPanel.add(defineClassButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        OGNLGetValueButton = new JButton();
        OGNLGetValueButton.setText("OGNL getValue");
        javaVulSearchPanel.add(OGNLGetValueButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        javaVulLabel = new JLabel();
        javaVulLabel.setText("Quickly Search Commons Java Vulnerabilities Call (todo)");
        javaVulSearchPanel.add(javaVulLabel, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        advancePanel.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        chatgptPanel = new JPanel();
        chatgptPanel.setLayout(new GridLayoutManager(3, 6, new Insets(0, 0, 0, 0), -1, -1));
        advancePanel.add(chatgptPanel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        chatgptPanel.setBorder(BorderFactory.createTitledBorder(null, "ChatGPT", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        apiKeyLabel = new JLabel();
        apiKeyLabel.setText("API KEY");
        chatgptPanel.add(apiKeyLabel, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        apiKeyHost = new JLabel();
        apiKeyHost.setText("HOST");
        chatgptPanel.add(apiKeyHost, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        apiKeyText = new JTextField();
        chatgptPanel.add(apiKeyText, new GridConstraints(0, 3, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        resultPanel = new JPanel();
        resultPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        chatgptPanel.add(resultPanel, new GridConstraints(2, 0, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        gptResultScroll = new JScrollPane();
        resultPanel.add(gptResultScroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 300), new Dimension(-1, 300), new Dimension(-1, 300), 0, false));
        gptResultArea = new JTextArea();
        gptResultArea.setLineWrap(true);
        gptResultScroll.setViewportView(gptResultArea);
        gptHostText = new JTextField();
        chatgptPanel.add(gptHostText, new GridConstraints(1, 3, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        gptStartBtn = new JButton();
        gptStartBtn.setText("Run");
        chatgptPanel.add(gptStartBtn, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(146, 30), null, 0, false));
        gptLabel = new JLabel();
        gptLabel.setText("");
        chatgptPanel.add(gptLabel, new GridConstraints(0, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        pluginsPanel = new JPanel();
        pluginsPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedPanel.addTab("plugins", pluginsPanel);
        piPanel = new JPanel();
        piPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        pluginsPanel.add(piPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        piPanel.setBorder(BorderFactory.createTitledBorder(null, "Plugins", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        encoderLabel = new JLabel();
        encoderLabel.setText("A tool for encode/decode encrypt/decrypt operations");
        piPanel.add(encoderLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        encoderBtn = new JButton();
        encoderBtn.setText("Start");
        piPanel.add(encoderBtn, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        repeaterLabel = new JLabel();
        repeaterLabel.setText("A tool for sending http request and get response");
        piPanel.add(repeaterLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        repeaterBtn = new JButton();
        repeaterBtn.setText("Start");
        piPanel.add(repeaterBtn, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listenerLabel = new JLabel();
        listenerLabel.setText("A tool for listening port and send by socket");
        piPanel.add(listenerLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        listenerBtn = new JButton();
        listenerBtn.setText("Start");
        piPanel.add(listenerBtn, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        pluginsPanel.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        corePanel.add(leftPanel, new GridConstraints(0, 0, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        treeScrollPanel = new JScrollPane();
        leftPanel.add(treeScrollPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(250, 850), new Dimension(250, 850), new Dimension(250, 850), 0, false));
        fileTree = new FileTree();
        treeScrollPanel.setViewportView(fileTree);
        logPanel = new JPanel();
        logPanel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        corePanel.add(logPanel, new GridConstraints(2, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        logScroll = new JScrollPane();
        logPanel.add(logScroll, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 100), null, null, 0, false));
        logScroll.setBorder(BorderFactory.createTitledBorder(null, "Log", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        logArea = new JTextArea();
        logArea.setBackground(new Color(-13619152));
        logArea.setEditable(false);
        logArea.setForeground(new Color(-16012544));
        logArea.setText("");
        logScroll.setViewportView(logArea);
        curMethodPanel = new JPanel();
        curMethodPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        logPanel.add(curMethodPanel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(600, -1), new Dimension(600, -1), new Dimension(600, -1), 0, false));
        allMethodScroll = new JScrollPane();
        curMethodPanel.add(allMethodScroll, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        allMethodList = new JList();
        allMethodScroll.setViewportView(allMethodList);
        curPanel = new JPanel();
        curPanel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        corePanel.add(curPanel, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        curPanel.setBorder(BorderFactory.createTitledBorder(null, "Current", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        curClassLabel = new JLabel();
        curClassLabel.setText("Class");
        curPanel.add(curClassLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        curClassText = new JTextField();
        curClassText.setEditable(false);
        curPanel.add(curClassText, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        curMethodLabel = new JLabel();
        curMethodLabel.setText("Method");
        curPanel.add(curMethodLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        curMethodText = new JTextField();
        curMethodText.setEditable(false);
        curPanel.add(curMethodText, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        curJarLabel = new JLabel();
        curJarLabel.setText("Jar");
        curPanel.add(curJarLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        curJarText = new JTextField();
        curJarText.setEditable(false);
        curPanel.add(curJarText, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(methodDefinitionRadioButton);
        buttonGroup.add(methodCallRadioButton);
        buttonGroup.add(stringContainsRadioButton);
        buttonGroup.add(binarySearchRadioButton);
        buttonGroup = new ButtonGroup();
        buttonGroup.add(fernRadio);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return masterPanel;
    }

}