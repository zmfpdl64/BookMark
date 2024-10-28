"use client"

import { useState, useEffect } from 'react'
import { Bookmark, Search, User, LogOut, UserPlus } from 'lucide-react'
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import Link from 'next/link'

interface AuthAction {
  action: 'signup' | 'signin';
  onStateChange: (newState: any) => void;
}

export default function Login({ onStateChange }: AuthAction) {


  return (
        <Card>
            <CardHeader>
              <CardTitle>환영합니다!</CardTitle>
            </CardHeader>
            <CardContent>
              <p className="mb-4">북마크를 관리하려면 로그인하거나 회원가입해주세요.</p>
              <div className="space-y-4">
                <Button onClick={() => onStateChange({ action: 'signin' })} className="w-full">
                  <User className="h-4 w-4 mr-2" />
                  Google로 로그인
                </Button>
                {/* <Button onClick={() => onStateChange({ action: 'signup' })} className="w-full" variant="outline">
                  <UserPlus className="h-4 w-4 mr-2" />
                  Google로 회원가입
                </Button> */}
              </div>
            </CardContent>
          </Card>
  )
}
