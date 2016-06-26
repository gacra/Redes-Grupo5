package cliente_redes;

import Comum.Candidato;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.text.MaskFormatter;

public class IntGrafUrna extends javax.swing.JFrame {

    public Urna urna;                   //Urna
    private IntGrafConect igConect;     //Tela inicial de conexão
    private Comunicador comunicador;    //Comunicador
    private AudioClip audio;            //Som da urna
    private ImageIcon icone;            //Ícone da urna

    /**
     * Creates new form IntGrafUrna
     *
     * @param urna
     */
    public IntGrafUrna(Urna urna) throws IOException, ClassNotFoundException, InterruptedException {
        this.urna = urna;

        igConect = new IntGrafConect(this, true);
        comunicador = new Comunicador(urna, igConect);
        Thread comunicador_t = new Thread(comunicador);
        comunicador_t.start();
        igConect.setVisible(true);

        initComponents();
        URL url = getClass().getResource("somUrna.wav");
        this.audio = Applet.newAudioClip(url);
        this.icone = new ImageIcon(getClass().getResource("figUrna.jpeg"));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("voting.png"));
        this.setIconImage(iconeTitulo);
        this.setTitle("Urna Eletrônica nº " + urna.getNum_urna());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                enviar.doClick();
                System.exit(0);
            }
        });
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch(Exception e) {
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        confirma = new javax.swing.JButton();
        branco = new javax.swing.JButton();
        nulo = new javax.swing.JButton();
        enviar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campoNome = new javax.swing.JTextField();
        campoPartido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoCod = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Urna Eletrônica");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        confirma.setBackground(new java.awt.Color(51, 255, 51));
        confirma.setOpaque(true);
        confirma.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        confirma.setText("Confirma");
        confirma.setBorderPainted(false);
        confirma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                confirmaMouseExited(evt);
            }
        });
        confirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmaActionPerformed(evt);
            }
        });

        branco.setBackground(new java.awt.Color(255, 255, 255));
        branco.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        branco.setOpaque(true);
        branco.setText("Branco");
        branco.setPreferredSize(new java.awt.Dimension(119, 33));
        branco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brancoActionPerformed(evt);
            }
        });

        nulo.setBackground(new java.awt.Color(255, 153, 0));
        nulo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        nulo.setOpaque(true);
        nulo.setText("Nulo");
        nulo.setBorderPainted(false);
        nulo.setPreferredSize(new java.awt.Dimension(119, 33));
        nulo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nuloMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nuloMouseExited(evt);
            }
        });
        nulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuloActionPerformed(evt);
            }
        });

        enviar.setBackground(new java.awt.Color(153, 153, 255));
        enviar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        enviar.setOpaque(true);
        enviar.setText("Enviar");
        enviar.setBorderPainted(false);
        enviar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                enviarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                enviarMouseExited(evt);
            }
        });
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("Código de votação:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Candidato:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Partido:");

        campoNome.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        campoNome.setEditable(false);
        campoNome.setOpaque(true);
        campoNome.setText("");

        campoPartido.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        campoPartido.setEditable(false);
        campoPartido.setOpaque(true);
        campoPartido.setText("");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Sistema de Votação Eletrônica");

        campoCod.setText("jFormattedTextField1");
        campoCod.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        try{
            MaskFormatter maskData = new MaskFormatter("##");
            maskData.install(campoCod);
        }catch(ParseException ex){}
        campoCod.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                campoCodCaretUpdate(evt);
            }
        });
        campoCod.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                campoCodMouseClicked(evt);
            }
        });
        campoCod.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                campoCodCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        campoCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCodActionPerformed(evt);
            }
        });
        campoCod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoCodKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(enviar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoCod, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(44, 44, 44)
                                    .addComponent(campoPartido))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(branco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(nulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(confirma)
                .addGap(62, 62, 62))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(campoCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(campoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(campoPartido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(branco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirma))
                .addGap(48, 48, 48)
                .addComponent(enviar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCodActionPerformed

    }//GEN-LAST:event_campoCodActionPerformed

    private void campoCodKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoCodKeyTyped

    }//GEN-LAST:event_campoCodKeyTyped

    /**
     * Trata o texto obtido pelo campo formatado campoCod.
     *
     * @param texto Texto não tratado.
     * @return Texto tratado.
     */
    private String trataTexto(String texto) {
        String texto2 = "";
        for(int i = 0; i < texto.length(); i++) {
            if(Character.isDigit(texto.charAt(i))) {
                texto2 = texto2.concat(String.valueOf(texto.charAt(i)));
            }
        }
        return texto2;
    }

    /**
     * Quando o cursor se move no campo do código de votação, os campos de nome
     * e partido do canditado são atualizados.
     *
     * @param evt
     */
    private void campoCodCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_campoCodCaretUpdate
        String texto = campoCod.getText();
        texto = trataTexto(texto);

        if(texto.length() > 1) {
            Candidato candidato = urna.encontraCandidato(Integer.parseInt(texto));
            if(candidato == null) {
                campoNome.setText("Desculpe. Não há candidato com esse código.");
                campoPartido.setText("");
            } else {
                campoNome.setText(candidato.getNome_candidato());
                campoPartido.setText(candidato.getPartido());
            }
        } else {
            campoNome.setText("");
            campoPartido.setText("");
        }
    }//GEN-LAST:event_campoCodCaretUpdate

    private void campoCodCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_campoCodCaretPositionChanged

    }//GEN-LAST:event_campoCodCaretPositionChanged

    /**
     * Quando o botão de enviar é clicado. Responsável por enviar os dados, se
     * possível, para o servidor.
     *
     * @param evt
     */
    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        if(urna.getTotal_votos() > 0) {
            if(comunicador.conectar()) {
                try {
                    comunicador.sConexao();
                    JOptionPane.showMessageDialog(igConect, "Os votos foram enviados.", "Obrigado", WIDTH);
                } catch(IOException ex) {
                    System.out.println("Erro na segunda conexão.");
                }
            } else {
                JOptionPane.showMessageDialog(igConect, "Os votos não puderam ser enviados. Provavelmente a votação já se encerrou.", "Desculpe.", NORMAL);
            }
        } else {
            JOptionPane.showMessageDialog(igConect, "Não é possível enviar os dados sem que haja ao menos um voto.", "Vote!!!", NORMAL);
            this.campoCod.requestFocus();
        }
    }//GEN-LAST:event_enviarActionPerformed

    /**
     * Fim de uma votação. Limpa os campos, reproduz o som da urna e gera janela
     * de confirmação.
     */
    private void fim() {
        campoCod.setText("");
        campoNome.setText("");
        campoPartido.setText("");
        audio.play();
        JOptionPane.showMessageDialog(igConect, null, "Seu voto foi computado.", WIDTH, icone);
        this.campoCod.requestFocus();
    }

    /**
     * Botão voto em branco clicado.
     *
     * @param evt
     */
    private void brancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brancoActionPerformed
        urna.votar_branco();
        fim();
    }//GEN-LAST:event_brancoActionPerformed

    /**
     * Botão voto nulo clicado.
     *
     * @param evt
     */
    private void nuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuloActionPerformed
        urna.votar_nulo();
        fim();
    }//GEN-LAST:event_nuloActionPerformed

    /**
     * Botão confirma clicado. Se houver um candidato válido nos campos, vota
     * nele.
     *
     * @param evt
     */
    private void confirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmaActionPerformed
        String texto = campoCod.getText();
        texto = trataTexto(texto);

        if(texto.length() > 1) {
            Candidato candidato = urna.encontraCandidato(Integer.parseInt(texto));
            if(candidato != null) {
                urna.votar(candidato);

                fim();
            }
        }
        this.campoCod.requestFocus();
    }//GEN-LAST:event_confirmaActionPerformed

    /**
     * Faz com que o cursor do campo de código esteja sempre na posição 0.
     *
     * @param evt
     */
    private void campoCodMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_campoCodMouseClicked
        this.campoCod.setCaretPosition(0);
    }//GEN-LAST:event_campoCodMouseClicked

    private void nuloMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuloMouseEntered
        this.nulo.setBackground(new Color(236, 141, 0));
    }//GEN-LAST:event_nuloMouseEntered

    private void confirmaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmaMouseEntered
        this.confirma.setBackground(new Color(0, 235, 0));
    }//GEN-LAST:event_confirmaMouseEntered

    private void confirmaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmaMouseExited
        this.confirma.setBackground(new Color(51, 255, 51));
    }//GEN-LAST:event_confirmaMouseExited

    private void nuloMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nuloMouseExited
        this.nulo.setBackground(new Color(255, 153, 0));
    }//GEN-LAST:event_nuloMouseExited

    private void enviarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enviarMouseEntered
        this.enviar.setBackground(new Color(113, 113, 255));
    }//GEN-LAST:event_enviarMouseEntered

    private void enviarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enviarMouseExited
        this.enviar.setBackground(new Color(153, 153, 255));
    }//GEN-LAST:event_enviarMouseExited

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton branco;
    private javax.swing.JFormattedTextField campoCod;
    private javax.swing.JTextField campoNome;
    private javax.swing.JTextField campoPartido;
    private javax.swing.JButton confirma;
    private javax.swing.JButton enviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton nulo;
    // End of variables declaration//GEN-END:variables
}
