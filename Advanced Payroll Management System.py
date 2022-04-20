import os
from tkinter import *
import tempfile
import random
import tkinter.messagebox
import datetime
import time
import tkinter

class Payroll:
 def __init__(self,root,):
    self.root=root
    self.root.title("Payroll System")
    self.root.geometry("1365x625+0+0")
    self.root.configure(background='black')

    EmployeeName=StringVar()
    Address=StringVar()
    Reference=StringVar()
    EmployerName=StringVar()
    CityWeighting=IntVar()
    BasicSalary=IntVar()
    OverTime=StringVar()
    GrossPay=StringVar()
    NetPay=StringVar()
    Tax=StringVar()
    Pension=StringVar()
    StudentLoan=StringVar()
    NIPayment=StringVar()
    Deductions=StringVar()
    PostCode=StringVar()
    Gender=StringVar()
    Payday=StringVar()
    TaxPeriod=StringVar()
    NINumber=StringVar()
    NICode=StringVar()
    TaxablePay=StringVar()
    PensionablePay=StringVar()
    OtherPaymentDue=StringVar()
    TaxCode=StringVar()
    
    CityWeighting.set("")
    BasicSalary.set("")
    #===========================================RestCommand===========================================
    def Reset() :
        Reset=tkinter.messagebox.askyesno("Payrool System","confirm if you want to Reset")
        if Reset>0:
         EmployeeName.set("")
         Address.set("")
         Reference.set('')
         EmployerName.set('')
         CityWeighting.set('')
         BasicSalary.set('')
         OverTime.set('')
         GrossPay.set('')
         NetPay.set('')
         Tax.set('')
         Pension.set('')
         StudentLoan.set('')
         NIPayment.set('')
         Deductions.set('')
         PostCode.set('')
         Gender.set('')
         Payday.set('')
         TaxPeriod.set('')
         NINumber.set('')
         NICode.set('')
         TaxablePay.set('')
         PensionablePay.set('')
         OtherPaymentDue.set('')
         TaxCode.set('')
         self.txtReceipt.delete("1.0",END)

    
    
      
    
    


#===========================================RestCommand=================================================
#===========================================ExitCommand=================================================
    def iExit():
        iExit=tkinter.messagebox.askyesno("Payrool System","confirm if you want to exit")
        if iExit>0:
            root.destroy()
            return

#===========================================/ExitCommand================================================
#===========================================RestCommand===========================================
    def Print() :
        q=self.txtReceipt.get("1.0","end-1c")
        filename = tempfile.mktemp(".txt")
        open (filename,"w").write(q)
        os.startfile(filename,"edit")

#===========================================Calculate Payroll===========================================
    def calculate():
        Payday.set(time.strftime("%d/%m/%Y"))
        Refpay=random.randint(20000,709494)
        Refpaid=("PR"+str(Refpay))
        Reference.set(Refpaid)

        NIpay=random.randint(34051,409123)
        NIpaid=("NI"+str(NIpay))
        NINumber.set(NIpaid)

        iDate=datetime.datetime.now()
        TaxPeriod.set(iDate.month)

        NCode=random.randint(1250,13123)
        CodeNI=("NICode"+str(NCode))
        NICode.set(CodeNI)

        iTaxCode=random.randint(1250,13123)
        PaymentTaxCode=("TCode"+str(iTaxCode))
        TaxCode.set(PaymentTaxCode)
    def MonthlySalary():
        calculate()
        BS=float(BasicSalary.get())
        CW=float(CityWeighting.get())
        OT=float(OverTime.get())

        MTax=(BS+CW+OT)*0.3
        TTax="$",str("%.2f"%(MTax))
        Tax.set(TTax)

        M_pension=((BS+CW+OT)*0.2)
        MM_pension="$", str("%.2f"%(M_pension))
        Pension.set(MM_pension)

        M_StudentLoan=((BS+CW+OT)*0.012)
        MM_StudentLoan="$", str("%.2f"%(M_StudentLoan))
        StudentLoan.set(MM_StudentLoan)


        M_NIPayment=((BS+CW+OT)*0.011)
        MM_NIPayment="$", str("%.2f"%(M_NIPayment))
        NIPayment.set(MM_NIPayment)

        M_NIPayment=((BS+CW+OT)*0.011)
        MM_NIPayment="$", str("%.2f"%(M_NIPayment))
        NIPayment.set(MM_NIPayment)

        Deduct=(MTax+M_pension+M_StudentLoan+M_NIPayment)
        Deduct_Payment="$", str("%.2f"%(Deduct))
        Deductions.set(Deduct_Payment)

        Gross_pay="$", str("%.2f"%(BS+CW+OT))
        GrossPay.set(Gross_pay)

        NetPayAfter=(BS+CW+OT)-Deduct
        NetAfter="$", str("%.2f"%(NetPayAfter))
        NetPay.set(NetAfter)
        TaxablePay.set(TTax)
        PensionablePay.set(MM_pension)
        OtherPaymentDue.set("0.00")

        self.txtReceipt.insert(END,'\t    Monthly Pay Slip'+"\n")
        self.txtReceipt.insert(END,'Reference: \t\t\t '+Reference.get()+"\n")
        self.txtReceipt.insert(END,'Reference: \t\t\t '+Payday.get()+"\n")
        self.txtReceipt.insert(END,'Employer Name: \t\t\t '+EmployerName.get()+"\n")
        self.txtReceipt.insert(END,'Employee Name: \t\t\t '+EmployeeName.get()+"\n")
        self.txtReceipt.insert(END,'Tax: \t\t\t '+Tax.get()+"\n")
        self.txtReceipt.insert(END,'Pension: \t\t\t '+Pension.get()+"\n")
        self.txtReceipt.insert(END,'Student Loan: \t\t\t '+StudentLoan.get()+"\n")
        self.txtReceipt.insert(END,'NI Number: \t\t\t '+NINumber.get()+"\n")
        self.txtReceipt.insert(END,'NI Payment: \t\t\t '+NIPayment.get()+"\n")
        self.txtReceipt.insert(END,'Deductions: \t\t\t '+Deductions.get()+"\n")
        self.txtReceipt.insert(END,'City Weighting: \t\t\t '+str('$ %.2f'%(CityWeighting.get()))+"\n")

        self.txtReceipt.insert(END,'\nTax: \t\t\t '+str('$ %.2f'%BasicSalary.get())+"\n")
        self.txtReceipt.insert(END,'OverTime: \t\t\t '+"$"+OverTime.get()+"\n")
        self.txtReceipt.insert(END,'NetPay: \t\t\t '+NetPay.get()+"\n")
        self.txtReceipt.insert(END,'GrossPay: \t\t\t '+GrossPay.get()+"\n")


        


#===========================================/Calculate Payroll===========================================




#===========================================layout===========================================
    MainFrame =Frame(self.root,bd=10,width=1350,height=700,bg="gainsboro",relief=RIDGE)
    MainFrame.grid()

    TopFrame1 =Frame(MainFrame,bd=10,width=1340,height=100,bg="gainsboro",relief=RIDGE)
    TopFrame1.grid()
    TopFrame2 =Frame(MainFrame,bd=10,width=1340,height=100,bg="gainsboro",relief=RIDGE)
    TopFrame2.grid()
    TopFrame3 =Frame(MainFrame,bd=10,width=1340,height=500,bg="gainsboro",relief=RIDGE)
    TopFrame3.grid()

    LeftFrame =Frame(TopFrame3,bd=5,width=1340,height=400,bg="gainsboro",relief=RIDGE,padx=2)
    LeftFrame.pack(side=LEFT)
    LeftFrame1 =Frame(LeftFrame,bd=5,width=600,height=180,bg="gainsboro",relief=RIDGE,padx=2)
    LeftFrame1.pack(side=TOP)

    LeftFrame2 =Frame(LeftFrame,bd=5,padx=2,width=600,height=180,bg="gainsboro",relief=RIDGE)
    LeftFrame2.pack(side=TOP)
    LeftFrame2Left =Frame(LeftFrame2,bd=5,width=300,height=170,bg="gainsboro",relief=RIDGE,padx=2)
    LeftFrame2Left.pack(side=LEFT)
    LeftFrame2Right=Frame(LeftFrame2,bd=5,width=300,height=170,bg="gainsboro",relief=RIDGE,padx=2)
    LeftFrame2Right.pack(side=RIGHT)

    LeftFrame3Left =Frame(LeftFrame,bd=5,width=320,height=50,bg="gainsboro",relief=RIDGE,padx=2)
    LeftFrame3Left.pack(side=LEFT)
    LeftFrame3Right=Frame(LeftFrame,bd=5,width=320,height=50,bg="gainsboro",relief=RIDGE,padx=2)
    LeftFrame3Right.pack(side=RIGHT)
    
    RightFrame1=Frame(TopFrame3,bd=5,width=320,height=400,bg="gainsboro",relief=RIDGE,padx=2)
    RightFrame1.pack(side=RIGHT)
    RightFrame1a=Frame(RightFrame1,bd=5,width=310,height=300,bg="gainsboro",relief=RIDGE,padx=2)
    RightFrame1a.pack(side=TOP)
    RightFrame1b=Frame(RightFrame1,bd=5,width=310,height=100,bg="gainsboro",relief=RIDGE,padx=2)
    RightFrame1b.pack(side=TOP)

    RightFrame2=Frame(TopFrame3,bd=5,width=300,height=400,bg="gainsboro",relief=RIDGE,padx=2)
    RightFrame2.pack(side=RIGHT)
    RightFrame2a=Frame(RightFrame2,bd=5,width=280,height=50,bg="gainsboro",relief=RIDGE,padx=2)
    RightFrame2a.pack(side=TOP)
    RightFrame2b=Frame(RightFrame2,bd=5,width=280,height=180,bg="gainsboro",relief=RIDGE,padx=2)
    RightFrame2b.pack(side=TOP)
    RightFrame2c=Frame(RightFrame2,bd=5,width=280,height=100,bg="gainsboro",relief=RIDGE,padx=2)
    RightFrame2c.pack(side=TOP)
    RightFrame2d=Frame(RightFrame2,bd=5,width=280,height=50,bg="gainsboro",relief=RIDGE,padx=2)
    RightFrame2d.pack(side=TOP)


    


#===================================/layout==================================================
#====================================Title===================================================
    self.lblTitle=Label(TopFrame1 ,font=('arial',40,'bold'),text="                     Payroll Management System                  ",
    bd=10,bg="gainsboro",justify=CENTER)
    self.lblTitle.grid(row=0,column=0)
#===================================/Title===================================================
#===================================Lable+TextFilde==========================================
#===============Topframe2
    self.EmployeeName=Label(TopFrame2 ,font=('arial',12,'bold'),text="Employee Name",bd=10,bg="gainsboro",anchor="sw",fg="red")
    self.EmployeeName.grid(row=0,column=0)
    self.txtEmployeeName=Entry(TopFrame2 ,font=('arial',12,'bold'),textvariable=EmployeeName,bd=5,bg="gainsboro",justify='left',width=59,background='white')
    self.txtEmployeeName.grid(row=0,column=1)

    self.lblAddress=Label(TopFrame2 ,font=('arial',12,'bold'),text="Address",bd=10,bg="gainsboro",anchor="sw",fg="red")
    self.lblAddress.grid(row=1,column=0)
    self.txtAddress=Entry(TopFrame2 ,font=('arial',12,'bold'),textvariable=Address,bd=5,bg="gainsboro",justify='left',width=59,background='white')
    self.txtAddress.grid(row=1,column=1)

    self.lblPostCode=Label(TopFrame2 ,font=('arial',12,'bold'),text="PostCode",bd=10,bg="gainsboro",anchor="w",fg="red")
    self.lblPostCode.grid(row=0,column=2)
    self.txtPostCode=Entry(TopFrame2 ,font=('arial',12,'bold'),textvariable=PostCode,bd=5,bg="gainsboro",justify='left',width=58,background='white')
    self.txtPostCode.grid(row=0,column=3)

    self.lblGender=Label(TopFrame2 ,font=('arial',12,'bold'),text="Gender",bd=10,bg="gainsboro",anchor="w",fg="red")
    self.lblGender.grid(row=1,column=2)
    self.txtGender=Entry(TopFrame2 ,font=('arial',12,'bold'),textvariable=Gender,bd=5,bg="gainsboro",justify='left',width=58,background='white')
    self.txtGender.grid(row=1,column=3)
#================LeftFrame1
    self.lblRefrence=Label(LeftFrame1 ,font=('arial',12,'bold'),text="Refrence",bd=10,bg="gainsboro",anchor="e")
    self.lblRefrence.grid(row=0,column=0)
    self.txtRefrence=Entry(LeftFrame1,font=('arial',12,'bold'),textvariable=Reference,bd=5,bg="gainsboro",justify='left',width=57,background='white')
    self.txtRefrence.grid(row=0,column=1)

    self.lblEmployeeName=Label(LeftFrame1 ,font=('arial',12,'bold'),text="Employer Name",bd=10,bg="gainsboro",anchor="w",fg="red")
    self.lblEmployeeName.grid(row=1,column=0)
    self.txtEmployeeName=Entry(LeftFrame1,font=('arial',12,'bold'),textvariable=EmployerName,bd=5,bg="gainsboro",justify='left',width=57,background='white')
    self.txtEmployeeName.grid(row=1,column=1)

    self.lblEmployeeName=Label(LeftFrame1 ,font=('arial',12,'bold'),text="Employee Name",bd=10,bg="gainsboro",anchor="w")
    self.lblEmployeeName.grid(row=2,column=0)
    self.txtEmployeeName=Entry(LeftFrame1,font=('arial',12,'bold'),textvariable=EmployeeName,bd=5,bg="gainsboro",justify='left',width=57,background='white')
    self.txtEmployeeName.grid(row=2,column=1)
#=======================LeftFrame2Left
    self.lblCityWeighting=Label(LeftFrame2Left ,font=('arial',12,'bold'),text="City Weighting",bd=10,bg="gainsboro",anchor="e",fg="red")
    self.lblCityWeighting.grid(row=0,column=0)
    self.txtCityWeighting=Entry(LeftFrame2Left,font=('arial',12,'bold'),textvariable=CityWeighting,bd=5,bg="gainsboro",justify='left',width=20,background='white')
    self.txtCityWeighting.grid(row=0,column=1)

    self.lblBasicSalary=Label(LeftFrame2Left ,font=('arial',12,'bold'),text="Basic Salary",bd=10,bg="gainsboro",anchor="w",fg="red")
    self.lblBasicSalary.grid(row=1,column=0)
    self.txtBasicSalary=Entry(LeftFrame2Left,font=('arial',12,'bold'),textvariable=BasicSalary,bd=5,bg="gainsboro",justify='left',width=20,background='white')
    self.txtBasicSalary.grid(row=1,column=1)

    self.lblOverTime=Label(LeftFrame2Left ,font=('arial',12,'bold'),text="Over Time",bd=10,bg="gainsboro",anchor="w",fg="red")
    self.lblOverTime.grid(row=2,column=0)
    self.txtOverTime=Entry(LeftFrame2Left,font=('arial',12,'bold'),textvariable=OverTime,bd=5,bg="gainsboro",justify='left',width=20,background='white')
    self.txtOverTime.grid(row=2,column=1)

    self.lblOtherPaymentDue=Label(LeftFrame2Left ,font=('arial',12,'bold'),text="Other Payment",bd=10,bg="gainsboro",anchor="w",fg="red")
    self.lblOtherPaymentDue.grid(row=3,column=0)
    self.txtOtherPaymentDue=Entry(LeftFrame2Left,font=('arial',12,'bold'),textvariable=OtherPaymentDue,bd=5,bg="gainsboro",justify='left',width=20,background='white')
    self.txtOtherPaymentDue.grid(row=3,column=1)

#==================LeftFrame2Right
    self.lblTax=Label(LeftFrame2Right ,font=('arial',12,'bold'),text="Tax",bd=10,bg="gainsboro",anchor="w")
    self.lblTax.grid(row=0,column=0)
    self.txtTax=Entry(LeftFrame2Right ,font=('arial',12,'bold'),textvariable=Tax,bd=5,bg="gainsboro",justify='left',width=20,background='white')
    self.txtTax.grid(row=0,column=1)

    self.lblPension=Label(LeftFrame2Right ,font=('arial',12,'bold'),text="Pension",bd=10,bg="gainsboro",anchor="w")
    self.lblPension.grid(row=1,column=0)
    self.txtPension=Entry(LeftFrame2Right,font=('arial',12,'bold'),textvariable=Pension,bd=5,bg="gainsboro",justify='left',width=20,background='white')
    self.txtPension.grid(row=1,column=1)

    self.lblStudentLoan=Label(LeftFrame2Right ,font=('arial',12,'bold'),text="Student Loan",bd=10,bg="gainsboro",anchor="w")
    self.lblStudentLoan.grid(row=2,column=0)
    self.txtStudentLoan=Entry(LeftFrame2Right,font=('arial',12,'bold'),textvariable=StudentLoan,bd=5,bg="gainsboro",justify='left',width=20,background='white')
    self.txtStudentLoan.grid(row=2,column=1)

    self.lblNIPayment=Label(LeftFrame2Right ,font=('arial',12,'bold'),text="NI Payment",bd=10,bg="gainsboro",anchor="w")
    self.lblNIPayment.grid(row=3,column=0)
    self.txtNIPayment=Entry(LeftFrame2Right,font=('arial',12,'bold'),textvariable=NIPayment,bd=5,bg="gainsboro",justify='left',width=20,background='white')
    self.txtNIPayment.grid(row=3,column=1)
#=====================Leftframe3Left&Right
    self.lblGrossPay=Label(LeftFrame3Left ,font=('arial',12,'bold'),text="Gross Pay",bd=10,bg="gainsboro",anchor="w")
    self.lblGrossPay.grid(row=3,column=0)
    self.txtGrossPay=Entry(LeftFrame3Left,font=('arial',12,'bold'),textvariable=GrossPay,bd=5,bg="gainsboro",justify='left',width=23,background='white')
    self.txtGrossPay.grid(row=3,column=1)

    self.lblDeductions=Label(LeftFrame3Right ,font=('arial',12,'bold'),text="Deductions",bd=10,bg="gainsboro",anchor="w")
    self.lblDeductions.grid(row=3,column=0)
    self.txtDeductions=Entry(LeftFrame3Right,font=('arial',12,'bold'),textvariable=Deductions,bd=5,bg="gainsboro",justify='left',width=23,background='white')
    self.txtDeductions.grid(row=3,column=1)
#===================Rightframe2a&b
    self.lblPayday=Label(RightFrame2a ,font=('arial',12,'bold'),text="Payday",bd=10,bg="gainsboro",anchor="w")
    self.lblPayday.grid(row=0,column=0)
    self.txtPayday=Entry(RightFrame2a  ,font=('arial',12,'bold'),textvariable=Payday,bd=5,bg="gainsboro",justify='left',width=19,background='white')
    self.txtPayday.grid(row=0,column=1)

    self.lblTaxPeriod=Label(RightFrame2b  ,font=('arial',12,'bold'),text="Tax Period",bd=10,bg="gainsboro",anchor="w")
    self.lblTaxPeriod.grid(row=0,column=0)
    self.txtTaxPeriod=Entry(RightFrame2b ,font=('arial',12,'bold'),textvariable=TaxPeriod,bd=5,bg="gainsboro",justify='left',width=16,background='white')
    self.txtTaxPeriod.grid(row=0,column=1)

    self.lblTaxCode=Label(RightFrame2b  ,font=('arial',12,'bold'),text="Tax Code",bd=10,bg="gainsboro",anchor="w")
    self.lblTaxCode.grid(row=1,column=0)
    self.txtTaxCode=Entry(RightFrame2b,font=('arial',12,'bold'),textvariable=TaxCode,bd=5,bg="gainsboro",justify='left',width=16,background='white')
    self.txtTaxCode.grid(row=1,column=1)

    self.lblNINumber=Label(RightFrame2b ,font=('arial',12,'bold'),text="NI Number",bd=10,bg="gainsboro",anchor="w")
    self.lblNINumber.grid(row=2,column=0)
    self.txtNINumber=Entry(RightFrame2b,font=('arial',12,'bold'),textvariable=NINumber,bd=5,bg="gainsboro",justify='left',width=16,background='white')
    self.txtNINumber.grid(row=2,column=1)

    self.lblNICode=Label(RightFrame2b ,font=('arial',12,'bold'),text="NI Code",bd=10,bg="gainsboro",anchor="w")
    self.lblNICode.grid(row=3,column=0)
    self.txtNICode=Entry(RightFrame2b,font=('arial',12,'bold'),textvariable=NICode,bd=5,bg="gainsboro",justify='left',width=16,background='white')
    self.txtNICode.grid(row=3,column=1)

    self.lblTaxablePay=Label(RightFrame2c  ,font=('arial',12,'bold'),text="Taxable Pay",bd=10,bg="gainsboro",anchor="w")
    self.lblTaxablePay.grid(row=0,column=0)
    self.txtTaxablePay=Entry(RightFrame2c ,font=('arial',12,'bold'),textvariable=TaxablePay,bd=5,bg="gainsboro",justify='left',width=11,background='white')
    self.txtTaxablePay.grid(row=0,column=1)

    self.lblPensionablePay=Label(RightFrame2c  ,font=('arial',12,'bold'),text="Pensionable Pay",bd=10,bg="gainsboro",anchor="w")
    self.lblPensionablePay.grid(row=1,column=0)
    self.txtPensionablePay=Entry(RightFrame2c,font=('arial',12,'bold'),textvariable=PensionablePay,bd=5,bg="gainsboro",justify='left',width=11,background='white')
    self.txtPensionablePay.grid(row=1,column=1)

    self.lblNetPay=Label(RightFrame2d  ,font=('arial',12,'bold'),text="Net Pay",bd=10,bg="gainsboro",anchor="w")
    self.lblNetPay.grid(row=0,column=0)
    self.txtNetPay=Entry(RightFrame2d ,font=('arial',12,'bold'),textvariable=NetPay,bd=5,bg="gainsboro",justify='left',width=18,background='white')
    self.txtNetPay.grid(row=0,column=1)

#===================================/Lable+TextFilde==========================================
#===================================         ==========================================
    self.txtReceipt=Text(RightFrame1a,height=18,width=40,bd=10,font=('arial',9,'bold'))
    self.txtReceipt.grid(row=0,column=0)

    self.btnPayment=Button(RightFrame1b,padx=18,pady=5,bd=5,font=('arial',12,'bold'),width=3,text='payment',command=MonthlySalary).grid(row=0,column=0)
    self.btnRest=Button(RightFrame1b,padx=18,pady=5,bd=5,font=('arial',12,'bold'),width=3,text='Print',command=Print).grid(row=0,column=1)
    self.btnRest=Button(RightFrame1b,padx=18,pady=5,bd=5,font=('arial',12,'bold'),width=3,text='Reset',command=Reset).grid(row=0,column=2)
    self.btnExit=Button(RightFrame1b,padx=18,pady=5,bd=5,font=('arial',12,'bold'),width=3,text='Exit',command=iExit).grid(row=0,column=3)







    



if __name__=='__main__':
    root=Tk()
    application=Payroll(root)
    root.mainloop()
