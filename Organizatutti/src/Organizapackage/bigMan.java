
package Organizapackage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class bigMan 
{
    // <editor-fold desc="CLASES" defaultstate="collapsed">
    class Operat
    {
        // <editor-fold desc="PROPERTIES OF OPERATIONS" defaultstate="collapsed">
        public int id;
        public double money;
        public boolean income; //false = outcome
        public String desc; //for description
        public Date date; 
        public int kind; //LEISURE, SUBSCRIPTION, FOOD, RENT, CLOTHING, GIFT, INVESTMENT, TRAVELING, STUFF, SALARY
        // </editor-fold>
        
        // <editor-fold desc="METHODS OF OPERATIONS" defaultstate="collapsed">
        //**************************************************************************
        public void Operat(int id, double money, boolean income, String desc, Date date, char kind)
        //**************************************************************************
        {    
            this.id = id;
            this.money = money;
            this.income = income;
            this.desc = desc;
            this.date = date;
            this.kind = kind;
        }
        
        //**************************************************************************
        private String KindToStr()
        //**************************************************************************
        {   
            String ret;
            switch(this.kind)
            {   
                case 2: ret = "SUBSCRIPTION";
                        break;
                case 3: ret = "FOOD";
                        break;
                case 4: ret = "RENT";
                        break;
                case 5: ret = "CLOTHING";
                        break;
                case 6: ret = "GIFT";
                        break;
                case 7: ret = "INVESTMENT";
                        break;
                case 8: ret = "TRAVELING";
                        break;
                case 9: ret = "STUFF";
                        break;
                case 10: ret = "SALARY";
                        break;
                default: ret = "LEISURE";
                        break;
            }
            return ret;
        }
        
        //**************************************************************************
        public String OperatToStr()
        //**************************************************************************
        {
            StringBuilder sb = new StringBuilder();
            
            String inc = "NO";
            if (this.income)
            {
                inc = "YES";
            }
            
            DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/mm/yyyy");  
            String strDate = dateFormat.format(this.date);  
            
            sb.append(String.format("Date: %s, ", strDate));
            sb.append(String.format("Id: %f, ", this.id));
            sb.append(String.format("Cost: %d, ", this.money));            
            sb.append(String.format("Income: %s, ", inc));
            sb.append(String.format("Description: %s, ", this.desc));          
            sb.append(String.format("Kind of: %s, ", this.KindToStr()));
            
            return sb.toString();
        }
        // </editor-fold>
    }

    class Accounts
    {
        // <editor-fold desc="PROPERTIES OF ACCOUNTS" defaultstate="collapsed">
        public int id;
        public String name;
        public String curr;
        public double money;
        public double monthly;
        private List<Operat> operats;
        // </editor-fold>
        
        // <editor-fold desc="METHODS OF ACCOUNTS" defaultstate="collapsed">
        //**************************************************************************
        public void Accounts(int id, String name, String curr)
        //**************************************************************************
        {    
            this.id = id;
            this.name = name;
            this.curr = curr;
            List<Operat> list = new ArrayList<>();
            list.clear();
            this.operats = list;
        }

        //**************************************************************************
        public void addOp(int id, double money, boolean income, String desc, Date today, char kind)
        //**************************************************************************
        {    
            //Date today = new Date();
            Operat op = new Operat();        
            op.Operat(id, money, income, desc, today, kind);

            int sum = -1;            
            if (income)
            {
                sum = 1;
            }            
            this.money += sum * money;

            this.operats.add(op);
        }
        
        //**************************************************************************
        public boolean delOp(int id)
        //**************************************************************************
        {
            int i = 0;        
            while ((this.operats.get(i).id == id) && (i<this.operats.size()))
            {
                i += 1;
            }
            
            if (this.operats.get(i).id == id)
            {
                int sum = -1;            
                if (this.operats.get(i).income)
                {
                    sum = 1;
                }            
                this.money += sum * money;

                this.operats.remove(i);
            } 

            return this.operats.get(i).id == id;
        }
        
        //**************************************************************************
        public List<Operat> printResume()
        //**************************************************************************
        {
            return this.operats;
        }
        
        //**************************************************************************
        public String accToStr()
        //**************************************************************************
        {            
            StringBuilder sb = new StringBuilder();

            sb.append(String.format("Id: %d, ", this.id));
            sb.append(String.format("Acc name: %s, ", this.name));
            sb.append(String.format("Currency: %s, ", this.curr));
            sb.append(String.format("Actual amount of money: %f, ", this.money));
            sb.append(String.format("Actual mothly spending: %f, ", this.monthly));
            sb.append(String.format("Detailed list of operations, total of: %d", this.operats.size()));
            int i = 0;
            while (i<this.operats.size())
            {
                sb.append(String.format(this.operats.get(i).OperatToStr()));
                i += 1;                
            }
            return sb.toString();
        }
        // </editor-fold>
    }
    // </editor-fold>
    
    // <editor-fold desc="PROPERTIES" defaultstate="collapsed">
    private double balance;    
    private List<Accounts> accs;    
    // </editor-fold>
    
    // <editor-fold desc="METHODS" defaultstate="collapsed"> 
    
    //**************************************************************************
    public void bigMan()
    //**************************************************************************
    {
        this.balance = 0.0;
        
        List<Accounts> list = new ArrayList<>();
        list.clear();
        this.accs = list;
    }
    
    //**************************************************************************
    private int getAccIndex(int id_pay)
    //**************************************************************************
    {
        if (this.accs.size() != 0)
        {
            int i = 0;
            while (i<this.accs.size() && this.accs.get(i).id != id_pay)
            {
                i+=1;
            }
            return i;
        }         
        return -1;
    }
    
    //**************************************************************************
    public void addAcc(int id, String name, String curr)
    //************************************************************************** 
    {
        Accounts acc = new Accounts();
        acc.Accounts(id, name, curr);
        this.accs.add(acc);
    }

    //**************************************************************************
    public void addOp(int id, double money, boolean income, String desc, Date today, char kind, int id_pay)
    //**************************************************************************   
    {
        if (getAccIndex(id_pay) > 0)
        {
            Accounts new_acc = this.accs.get(getAccIndex(id_pay));
            new_acc.addOp(id, money, income, desc, today, kind);
            this.accs.set(getAccIndex(id_pay), new_acc);       
        } 
    }
        
    //**************************************************************************
    public String manToStr()
    //************************************************************************** 
    {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Big mans balance: %f, ", this.balance));
        sb.append(String.format("Big mans accounts detail, total of: %d, ", this.accs.size()));
        int i = 0;
        while (i<this.accs.size())
        {
            sb.append(String.format(this.accs.get(i).accToStr()));
            i += 1;                
        }
        return sb.toString();       
    }
    
    //**************************************************************************
    public void saveBigMan(String dir)
    //************************************************************************** 
    {
        File f = new File(dir);
        
        try //**************************************************************************
        {        
            if (!f.exists())
            {
                try {
                    File directory = new File(f.getParent());
                    if (!directory.exists())
                    {
                        directory.mkdirs();
                    }
                    f.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(bigMan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            FileWriter fw;
            fw = new FileWriter(f.getAbsoluteFile(), true);
            
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.manToStr());
            bw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(bigMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //**************************************************************************
    public String loadBigMan(String dir)
    //**************************************************************************
    {
        char[] bm = new char[100];
        try {
            FileReader f = new FileReader(dir);
            f.read(bm);
            f.close();            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(bigMan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(bigMan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bm.toString();        
    }
    
    //**************************************************************************
    public void decodeBigMan(String bm)
    //**************************************************************************
    {
        
    }
    // </editor-fold>
}
